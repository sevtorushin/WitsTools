package filters;

import exceptions.WitsParseException;
import parsers.WitsPackageParser;
import parsers.WitsParsersProvider;

import java.util.List;

public class PackageFilter implements Filter {

    private List<String> packageNumbers;
    private WitsParsersProvider packageProvider;

    public PackageFilter(WitsParsersProvider packageProvider, String... packageNumbers) {
        this.packageNumbers = List.of(packageNumbers);
        this.packageProvider = packageProvider;
    }

    public String filtrate(String witsPackage) {
        WitsPackageParser parser = (WitsPackageParser) packageProvider.getParserForPackage(witsPackage);
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
}
