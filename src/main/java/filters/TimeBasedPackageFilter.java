package filters;

import java.util.Arrays;
import java.util.List;

public class TimeBasedPackageFilter {

    private List<String> items;

    public TimeBasedPackageFilter(String... items) {
        this.items = List.of(items);
    }

    public String filtrate(String witsPackage) {
        String[] tokens = witsPackage.split("\r?\n|\r");
        StringBuilder resultPackage = new StringBuilder("&&\r\n");
        Arrays.stream(tokens)
                .filter(s -> !s.equals("&&")&&!s.equals("!!"))
                .filter(s -> items.contains(s.substring(2,4)))
                .forEach(s -> resultPackage.append(s).append("\r\n"));
        resultPackage.append("!!");
        return resultPackage.toString();
    }
}
