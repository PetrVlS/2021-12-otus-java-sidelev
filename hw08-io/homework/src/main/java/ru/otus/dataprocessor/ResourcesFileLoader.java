package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import javax.json.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourcesFileLoader implements Loader {
    String fileName;

    public ResourcesFileLoader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Measurement> load() {
        List<Measurement> measurements = new ArrayList<>();
        try (var jsonReader = Json.createReader(ResourcesFileLoader.class.getClassLoader().getResourceAsStream(fileName))) {
            JsonArray jsonArray = jsonReader.readArray();
            for (JsonValue jsonObject : jsonArray) {
                var name = jsonObject.asJsonObject().getString("name");
                var value = jsonObject.asJsonObject().getInt("value");
                measurements.add(new Measurement(name, value));
            }
        }
        return measurements;
    }
}
