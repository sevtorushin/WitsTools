package filters;

import descriptions.TimeBasedDescription;
import generators.TimeBasedGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class TimeBasedBuilder {
    private String packageNumber = "01";
    private TimeBasedGenerator generator;
    private Map<String, String> map = new TreeMap<>(String::compareTo);

    public TimeBasedBuilder(TimeBasedGenerator generator) {
        this.generator = generator;
        Arrays.stream(TimeBasedDescription.values()).forEach(td -> map.put(td.getItem(), "0"));
    }

    public TimeBasedBuilder(TimeBasedGenerator generator, String... items) {
        this.generator = generator;
        Arrays.stream(items).forEach(s -> map.put(s, "0"));
    }

    public String build() {
        resetMap();
        StringBuilder builder = new StringBuilder("&&\r\n");
        map.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .forEach(entry -> builder
                        .append(packageNumber)
                        .append(entry.getKey())
                        .append(entry.getValue())
                        .append("\r\n"));
        builder.append("!!");
        return builder.toString();
    }

    private void resetMap() {
        map.forEach((key, value) -> {
            try {
                map.put(key, generator.getValueForItem(key));
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}