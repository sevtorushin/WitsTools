package filters;

import descriptions.WitsDescriptor;
import exceptions.WitsParseException;
import parsers.WitsPackageParser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class ItemFilter<T extends WitsDescriptor> implements Filter {
    protected Set<String> items;
    protected WitsPackageParser packageParser;

    public ItemFilter(Set<T> descriptors, WitsPackageParser parser) {
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

    public void addItem(Optional<T> descriptor){
        descriptor.ifPresent(t -> items.add(t.getItem()));
    }

    public void addItems(Set<T> descriptors){
        this.items.addAll(descriptors.stream().map(WitsDescriptor::getItem).collect(Collectors.toSet()));
    }

    public void removeItem(Optional<T> descriptor){
        descriptor.ifPresent(t -> items.remove(t.getItem()));
    }

    public void removeItems(Set<T> descriptors){
        this.items.removeAll(descriptors.stream().map(WitsDescriptor::getItem).collect(Collectors.toSet()));
    }

    public Set<String> getItems() {
        return Collections.unmodifiableSet(items);
    }

    public void setItems(Set<T> descriptors) {
        items = descriptors.stream().map(WitsDescriptor::getItem).collect(Collectors.toSet());
    }

    public WitsPackageParser getPackageParser() {
        return packageParser;
    }

    public void setPackageParser(WitsPackageParser packageParser) {
        this.packageParser = packageParser;
    }
}
