package ru.otus.controllers;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.otus.dto.ClientDTO;

import java.util.List;

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
        model.addAttribute("clientDTO", new ClientDTO());
        return client.get().uri("/clients")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ClientDTO>>() {
                })
                .doOnNext(clientDTOList -> model.addAttribute("clients", clientDTOList))
                .then(Mono.just("clients"));
    }

}
