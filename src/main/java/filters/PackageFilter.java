package filters;

import java.util.List;

public class PackageFilter implements Filter {

    private List<String> packageNumbers;

    public PackageFilter(String... packageNumbers) {
        this.packageNumbers = List.of(packageNumbers);
    }

    public String filtrate(String witsPackage) {
        String[] tokens = witsPackage.split("\r?\n|\r");
        String packageNumber = tokens[1].substring(0, 2);
        if (!packageNumbers.contains(packageNumber))
            return null;
        return witsPackage;
    }
}
