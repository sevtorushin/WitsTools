package filters;

import descriptions.TimeBasedDescription;
import exceptions.WitsParseException;
import parsers.TimeBasedPackageParser;

import java.util.*;

public class TimeBasedItemFilter implements Filter{

    private Set<String> items;
    private TimeBasedPackageParser packageParser;

    public TimeBasedItemFilter(TimeBasedDescription... descriptors) {
        this.items = new HashSet<>();
        Arrays.stream(descriptors).forEach(description -> items.add(description.getItem()));
        this.packageParser = new TimeBasedPackageParser();
        packageParser.resetValidation();
    }

    public TimeBasedItemFilter() {
        this.items = new HashSet<>(TimeBasedDescription.WELL_IDENTIFIER.getItemSet());
        this.packageParser = new TimeBasedPackageParser();
        packageParser.resetValidation();
    }

    public String filtrate(String witsPackage) {
        try {
            packageParser.parse(witsPackage);
        } catch (WitsParseException e) {
            e.printStackTrace();
        }
        StringBuilder resultPackage = new StringBuilder("&&\r\n");
        items.stream()
                .filter(item -> {
                    try {
                        return packageParser.getValue(item)!=null;
                    } catch (WitsParseException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .sorted()
                .forEach(item -> {
                    try {
                        resultPackage
                                .append(packageParser.getPackageNumber())
                                .append(item)
                                .append(packageParser.getValue(item))
                                .append("\r\n");
                    } catch (WitsParseException e) {
                        e.printStackTrace();
                    }
                });
        resultPackage.append("!!");
        return resultPackage.toString();
    }

    public void addItem(String item){
        this.items.add(item);
    }

    public void addItems(String... item){
        this.items.addAll(Arrays.asList(item));
    }

    public void removeItem(String item){
        this.items.remove(item);
    }

    public void removeItems(String... item){
        this.items.retainAll(Arrays.asList(item));
    }

    public Set<String> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public void setItems(Set<String> items) {
        this.items = items;
    }

    public TimeBasedPackageParser getPackageParser() {
        return packageParser;
    }

    public void setPackageParser(TimeBasedPackageParser packageParser) {
        this.packageParser = packageParser;
    }
}
