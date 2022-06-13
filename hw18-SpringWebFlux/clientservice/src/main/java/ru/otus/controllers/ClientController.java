package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.services.ClientServiceValue;
import ru.otus.services.ClientService;

//http://localhost:8081/clients

@Controller
public class ClientController {

    private final WebClient client;

    public ClientController(WebClient.Builder builder) {
        client = builder
                .baseUrl("http://localhost:8080")
                .build();
    }

    @GetMapping("/clients")
    public Mono<String> clientsListView(Model model) {
        model.addAttribute("clientService", new ClientService());
        return client.get().uri("/clients")
                .retrieve()
                .bodyToMono(ClientServiceValue.class)
                .doOnNext(clientServiceValue -> model.addAttribute("clients", clientServiceValue.clientServiceList()))
                .then(Mono.just("clients"));
    }

}
