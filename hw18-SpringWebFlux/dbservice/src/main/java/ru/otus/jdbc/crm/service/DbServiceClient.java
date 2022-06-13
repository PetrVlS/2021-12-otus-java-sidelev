package ru.otus.jdbc.crm.service;

import ru.otus.jdbc.crm.model.Client;

import java.util.List;
import java.util.Optional;

public interface DbServiceClient {

    Client saveClient(Client client);

    List<Client> findAll();
}
