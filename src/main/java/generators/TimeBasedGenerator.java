package generators;

import descriptions.TimeBasedDescription;
import generators.functions.LineFunction;
import parsers.TimeBasedParser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TimeBasedGenerator {

    private Map<String, String> map = new HashMap<>();
    TimeBasedParser parser = new TimeBasedParser();

    public TimeBasedGenerator(String... items) {
        Arrays.stream(items).forEach(s -> map.put(s, null));
    }

    public TimeBasedGenerator() {
        Arrays.stream(TimeBasedDescription.values()).forEach(td -> map.put(td.getItem(), null));
    }

    public String getBPOS(LineFunction function) {
        return null;
    }
}
