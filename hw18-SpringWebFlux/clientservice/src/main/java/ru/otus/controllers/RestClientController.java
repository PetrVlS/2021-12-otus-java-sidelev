package ru.otus.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.services.ClientService;


@RestController
public class RestClientController {

    @PostMapping("/client/save")
    public Mono<ClientService> clientSave(@ModelAttribute ClientService clientService) {
        return Mono.just(clientService);
    }
}
