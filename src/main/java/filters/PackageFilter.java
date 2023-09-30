package filters;

import exceptions.WitsPackageException;

import java.util.List;

public class PackageFilter {

    private List<String> packageNumbers;

    public PackageFilter(String... packageNumbers) {
        this.packageNumbers = List.of(packageNumbers);
    }

    public String filtrate(String witsPackage) throws WitsPackageException {
        String[] tokens = witsPackage.split("\r?\n|\r");
        String packageNumber = tokens[1].substring(0, 2);
        if (!packageNumbers.contains(packageNumber))
            throw new WitsPackageException("Wrong package");
        return witsPackage;
    }
}
