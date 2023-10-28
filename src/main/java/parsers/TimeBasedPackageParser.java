package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsPackageException;
import parsers.splitters.PackageSplitter;
import validators.*;

import java.util.List;

@Package(number = "01")
public class TimeBasedPackageParser extends WitsPackageParser {

    public TimeBasedPackageParser() {
        super(new TimeBasedRecordParser(), new PackageSplitter("\\r?\\n|\\n"), new ValidatorBuilder<>(List.of(new TimeBasedPackageValidator())));
    }

    public TimeBasedPackageParser(ValidatorBuilder<TimeBasedPackageValidator> packageValidatorBuilder) {
        super(new TimeBasedRecordParser(), new PackageSplitter("\\r?\\n|\\n"), packageValidatorBuilder);
    }

    public TimeBasedPackageParser(ValidatorBuilder<TimeBasedPackageValidator> packageValidatorBuilder, ValidatorBuilder<TimeBasedRecordValidator> recordValidatorBuilder) {
        super(new TimeBasedRecordParser(recordValidatorBuilder), new PackageSplitter("\\r?\\n|\\n"), packageValidatorBuilder);
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
