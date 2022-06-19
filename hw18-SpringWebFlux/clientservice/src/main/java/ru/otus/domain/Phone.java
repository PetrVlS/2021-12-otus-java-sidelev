package ru.otus.domain;


public class Phone {


    private final Long id;

    private final String number;

    private final Long clientId;

    public Phone(String number, Long clientId) {
        this(null, number, clientId);
    }


    public Phone(Long id, String number, Long clientId) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Long getClientId() {
        return clientId;
    }
}


