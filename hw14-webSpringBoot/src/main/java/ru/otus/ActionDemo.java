package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.otus.jdbc.core.repository.ClientRepository;
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
        dbServiceClient.saveClient(new Client("Илья", new Address("ул. Набережная 17", null),
                Set.of(new Phone("8(935) 456-62-78", null), new Phone("8(632) 652-45-81", null))));
        dbServiceClient.saveClient(new Client("Анна", new Address("ул. Ленина 93", null),
                Set.of(new Phone("8(915) 246-62-85", null), new Phone("8(332) 453-68-71", null))));
        dbServiceClient.saveClient(new Client("Альберт", new Address("ул. Кирова 12", null),
                Set.of(new Phone("8(925) 758-62-86", null), new Phone("8(123) 235-35-56", null))));
    }
}
