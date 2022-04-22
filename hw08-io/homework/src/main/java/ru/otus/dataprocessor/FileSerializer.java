package ru.otus.dataprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileSerializer implements Serializer {
    String fileName;

    public FileSerializer(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void serialize(Map<String, Double> data) throws IOException {
        var file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(file, data);
        //формирует результирующий json и сохраняет его в файл
    }
}
