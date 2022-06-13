package ru.otus.domain;


public class Address {


    private final Long id;

    private final String street;

    private final Long clientId;

    public Address(String street, Long clientId) {
        this(null, street, clientId);
    }


    public Address(Long id, String street, Long clientId) {
        this.id = id;
        this.street = street;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public Long getClientId() {
        return clientId;
    }
}


