package filters;

import exceptions.WitsParseException;
import parsers.WitsPackageParser;
import parsers.WitsParsersProvider;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class PackageFilter implements Filter {

    private Set<String> packageNumbers;
    private WitsParsersProvider parsersProvider;

    public PackageFilter(String... packageNumbers) {
        this.packageNumbers = new HashSet<>(Set.of(packageNumbers));
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

    public void addPackageNumber(String packageNumber) {
        this.packageNumbers.add(packageNumber);
    }

    public void addPackageNumbers(String... packageNumbers) {
        this.packageNumbers.addAll(Arrays.asList(packageNumbers));
    }

    public void removePackageNumber(String packageNumber) {
        this.packageNumbers.remove(packageNumber);
    }

    public void removePackageNumbers(String... packageNumbers) {
        this.packageNumbers.removeAll(Arrays.asList(packageNumbers));
    }

    public Set<String> getPackageNumbers() {
        return Collections.unmodifiableSet(packageNumbers);
    }

    public void setPackageNumbers(Set<String> packageNumbers) {
        this.packageNumbers = packageNumbers;
    }

    public WitsParsersProvider getParsersProvider() {
        return parsersProvider;
    }

    public void setParsersProvider(WitsParsersProvider parsersProvider) {
        this.parsersProvider = parsersProvider;
    }
}
