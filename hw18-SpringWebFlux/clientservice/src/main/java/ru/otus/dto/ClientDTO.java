package ru.otus.dto;

import ru.otus.domain.Client;
import ru.otus.domain.Phone;

import java.util.stream.Collectors;

public class ClientDTO {
    private String id;
    private String name;
    private String address;
    private String phones;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        id = client.getId().toString();
        name = client.getName();
        address = client.getAddress().getStreet();
        phones = client.getPhones().stream().map(Phone::getNumber).collect(Collectors.joining("; "));
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhones() {
        return phones;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientDTO that = (ClientDTO) o;

        if (!id.equals(that.id)) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return phones != null ? phones.equals(that.phones) : that.phones == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        return result;
    }
}
