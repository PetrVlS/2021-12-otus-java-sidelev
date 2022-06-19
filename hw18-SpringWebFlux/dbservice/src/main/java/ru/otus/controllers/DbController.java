package ru.otus.controllers;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import ru.otus.jdbc.crm.service.DbServiceClient;
import ru.otus.dto.ClientDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DbController {
    private final DbServiceClient dbServiceClient;

    public DbController(DbServiceClient dbServiceClient, WebClient.Builder builder) {
        this.dbServiceClient = dbServiceClient;
    }

    @GetMapping("/clients")
    public Mono<List<ClientDTO>> clientsListView() {
        var clientDTOList = dbServiceClient.findAll().stream().map(ClientDTO::new).collect(Collectors.toList());
        return Mono.just(clientDTOList);
    }

}
