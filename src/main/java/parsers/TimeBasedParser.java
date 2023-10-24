package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsPackageException;
import validators.TimeBasedPackageValidator;

@Package(number = "01")
public class TimeBasedParser extends WitsPackageParser {

    public TimeBasedParser() {
        super(new RecordSplitter(2, 2), new PackageSplitter("\\r?\\n|\\n"), new TimeBasedPackageValidator());
    }

    @Item(number = "08")
    public Double getBitDepth() throws WitsPackageException {
        return getDoubleValue("08");
    }

    @Item(number = "10")
    public Double getMeasuredDepth() throws WitsPackageException {
        return getDoubleValue("10");
    }

    @Item(number = "12")
    public Double getBlockPos() throws WitsPackageException {
        return getDoubleValue("12");
    }

    @Item(number = "14")
    public Double getHKLD() throws WitsPackageException {
        return getDoubleValue("14");
    }

    @Item(number = "21")
    public Double getPressure() throws WitsPackageException {
        return getDoubleValue("21");
    }
}
