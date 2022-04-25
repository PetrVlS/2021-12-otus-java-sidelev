package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.*;
import java.util.stream.Collectors;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        var processMap = data.stream().
                collect(Collectors.groupingBy(Measurement::getName,
                        Collectors.summingDouble(Measurement::getValue)));
        return new TreeMap<>(processMap);
    }
}
