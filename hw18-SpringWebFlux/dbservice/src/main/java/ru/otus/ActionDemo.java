package ru.otus;

import org.springframework.stereotype.Component;
import ru.otus.jdbc.crm.model.Address;
import ru.otus.jdbc.crm.model.Client;
import ru.otus.jdbc.crm.model.Phone;
import ru.otus.jdbc.crm.service.DbServiceClient;

import java.util.Set;

@Component("actionDemo")
public class ActionDemo {

    private final DbServiceClient dbServiceClient;

    public ActionDemo(DbServiceClient dbServiceClient) {
        this.dbServiceClient = dbServiceClient;
    }

    void action() {
        dbServiceClient.saveClient(new Client("Вася", new Address("ул. Лесной проспект 16", null),
                Set.of(new Phone("8(999) 665-71-98", null), new Phone("8(923) 659-98-26", null))));
    }
}
