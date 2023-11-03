package filters;

import descriptions.WitsDescriptor;
import exceptions.WitsParseException;
import parsers.WitsPackageParser;
import parsers.WitsParsersProvider;

import java.util.*;
import java.util.stream.Collectors;

public class PackageFilter implements Filter {

    private final Set<String> packageNumbers;
    private final WitsParsersProvider parsersProvider;

    public PackageFilter(Set<Class<? extends WitsDescriptor>> descriptors) {
        this.packageNumbers = descriptors.stream()
                .map(aClass -> aClass.getEnumConstants()[0].getPackageNumber())
                .collect(Collectors.toSet());
        this.parsersProvider = new WitsParsersProvider();
    }

    public String filtrate(String witsPackage) {
        WitsPackageParser parser = (WitsPackageParser) parsersProvider.getParserForPackage(witsPackage);
        parser.resetValidation();
        try {
            parser.parse(witsPackage);
            if (!packageNumbers.contains(parser.getPackageNumber()))
                return null;
        } catch (WitsParseException e) {
            e.printStackTrace();
            return null;
        }
        return witsPackage;
    }

    public void addPackageNumber(WitsDescriptor descriptor) {
        this.packageNumbers.add(descriptor.getPackageNumber());
    }

    public void addPackageNumbers(Set<WitsDescriptor> descriptors) {
        this.packageNumbers.addAll(descriptors.stream().map(WitsDescriptor::getPackageNumber).collect(Collectors.toSet()));
    }

    public void removePackageNumber(WitsDescriptor descriptor) {
        this.packageNumbers.remove(descriptor.getPackageNumber());
    }

    public void removePackageNumbers(Set<WitsDescriptor> descriptors) {
        this.packageNumbers.removeAll(descriptors.stream().map(WitsDescriptor::getPackageNumber).collect(Collectors.toSet()));
    }

    public Set<String> getPackageNumbers() {
        return Collections.unmodifiableSet(packageNumbers);
    }

    public WitsParsersProvider getParsersProvider() {
        return parsersProvider;
    }
}
