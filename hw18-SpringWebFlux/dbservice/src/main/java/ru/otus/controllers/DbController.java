package ru.otus.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.jdbc.crm.model.Address;
import ru.otus.jdbc.crm.model.Client;
import ru.otus.jdbc.crm.model.Phone;
import ru.otus.services.ClientServiceValue;
import ru.otus.jdbc.crm.service.DbServiceClient;
import ru.otus.services.ClientService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class DbController {
    private final DbServiceClient dbServiceClient;
    private final WebClient client;

    public DbController(DbServiceClient dbServiceClient, WebClient.Builder builder) {
        this.dbServiceClient = dbServiceClient;
        client = builder
                .baseUrl("http://localhost:8081")
                .build();
    }

    @GetMapping("/clients")
    public Mono<ClientServiceValue> clientsListView() {
        var clientServiceList = dbServiceClient.findAll().stream().map(ClientService::new).collect(Collectors.toList());
        var clientServiceValue = new ClientServiceValue(clientServiceList);
        return Mono.just(clientServiceValue);
    }

    @PostMapping("/client/save")
    public void clientSave() {
        client.post().uri("/client/save")
                .retrieve()
                .bodyToMono(ClientService.class)
                .doOnNext((clientService) -> {
                            var name = clientService.getName();
                            var address = new Address(clientService.getAddress(), null);
                            var phones = Stream.of(clientService.getPhones().split(";")).map(number -> new Phone(number, null)).collect(Collectors.toSet());
                            dbServiceClient.saveClient(new Client(name, address, phones));
                        }
                );
    }
}
