package generators.builders;

import descriptions.TimeBasedDescription;
import generators.wits.WitsGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class WitsPackageBuilder {
    private final String packageNumber;
    private final Map<String, String> map = new TreeMap<>(String::compareTo);
    private final WitsGenerator generator;

    public WitsPackageBuilder(String packageNumber, WitsGenerator generator) {
        this.packageNumber = packageNumber;
        this.generator = generator;
        Arrays.stream(TimeBasedDescription.values()).forEach(td -> map.put(td.getItem(), "0"));
    }

    public WitsPackageBuilder(String packageNumber, WitsGenerator generator, String... items) {
        this.packageNumber = packageNumber;
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public WitsGenerator getGenerator() {
        return generator;
    }
}
