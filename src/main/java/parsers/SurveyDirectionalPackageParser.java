package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsParseException;
import parsers.splitters.PackageSplitter;
import validators.*;

import java.util.ArrayList;
import java.util.List;

@Package(number = "07")
public class SurveyDirectionalPackageParser extends WitsPackageParser {

    public SurveyDirectionalPackageParser() {
        super(new SurveyDirectionalRecordParser(),
                new PackageSplitter("\\r?\\n|\\r"),
                new ValidatorBuilder<>(new ArrayList<>(List.of(new SurveyDirectionalPackageValidator()))));
    }

    public SurveyDirectionalPackageParser(ValidatorBuilder<SurveyDirectionalPackageValidator> packageValidatorBuilder) {
        super(new SurveyDirectionalRecordParser(),
                new PackageSplitter("\\r?\\n|\\n"),
                packageValidatorBuilder);
    }

    public SurveyDirectionalPackageParser(ValidatorBuilder<SurveyDirectionalPackageValidator> packageValidatorBuilder,
                                  ValidatorBuilder<SurveyDirectionalRecordValidator> recordValidatorBuilder) {
        super(new SurveyDirectionalRecordParser(recordValidatorBuilder),
                new PackageSplitter("\\r?\\n|\\n"),
                packageValidatorBuilder);
    }

    @Item(number = "08")
    public Double getSurveyMD(String witsPackage) throws WitsParseException {
        return getDoubleValue("08");
    }

    @Item(number = "13")
    public Double getInclination(String witsPackage) throws WitsParseException {
        return getDoubleValue("13");
    }

    @Item(number = "15")
    public Double getAzimuthCorr(String witsPackage) throws WitsParseException {
        return getDoubleValue("15");
    }

    @Item(number = "16")
    public Double getMagneticTF(String witsPackage) throws WitsParseException {
        return getDoubleValue("16");
    }

    @Item(number = "17")
    public Double getGravityTF(String witsPackage) throws WitsParseException {
        return getDoubleValue("17");
    }
}
