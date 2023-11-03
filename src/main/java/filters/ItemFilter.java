package filters;

import descriptions.WitsDescriptor;
import exceptions.WitsParseException;
import parsers.TimeBasedPackageParser;
import parsers.WitsPackageParser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class ItemFilter<T extends WitsDescriptor> implements Filter {
    protected Set<String> items;
    protected WitsPackageParser packageParser;

    public ItemFilter(List<T> descriptors, WitsPackageParser parser) {
        this.items = new HashSet<>();
        descriptors.forEach(description -> items.add(description.getItem()));
        this.packageParser = parser;
        packageParser.resetValidation();
    }

    public ItemFilter(WitsPackageParser parser) {
        try {
            this.items = new HashSet<>(getType().getDeclaredConstructor().newInstance().getItemSet());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        this.packageParser = parser;
        packageParser.resetValidation();
    }

    public abstract Class<T> getType();

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
                        return packageParser.getValue(item) != null;
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

    public WitsPackageParser getPackageParser() {
        return packageParser;
    }

    public void setPackageParser(TimeBasedPackageParser packageParser) {
        this.packageParser = packageParser;
    }
}
