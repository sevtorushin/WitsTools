package parsers;

import annotation.Item;
import annotation.Package;
import exceptions.WitsParseException;
import parsers.splitters.PackageSplitter;
import validators.*;

import java.util.ArrayList;
import java.util.List;

@Package(number = "08")
public class MwdEvaluationPackageParser extends WitsPackageParser {

    public MwdEvaluationPackageParser() {
        super(new MwdEvaluationRecordParser(),
                new PackageSplitter("\\r?\\n|\\r"),
                new ValidatorBuilder<>(new ArrayList<>(List.of(new MwdEvaluationPackageValidator()))));
    }

    public MwdEvaluationPackageParser(ValidatorBuilder<MwdEvaluationPackageValidator> packageValidatorBuilder) {
        super(new MwdEvaluationRecordParser(),
                new PackageSplitter("\\r?\\n|\\n"),
                packageValidatorBuilder);
    }

    public MwdEvaluationPackageParser(ValidatorBuilder<MwdEvaluationPackageValidator> packageValidatorBuilder,
                                      ValidatorBuilder<MwdEvaluationRecordValidator> recordValidatorBuilder) {
        super(new MwdEvaluationRecordParser(recordValidatorBuilder),
                new PackageSplitter("\\r?\\n|\\n"),
                packageValidatorBuilder);
    }

    @Item(number = "21")
    public Double getGammaMD(String witsPackage) throws WitsParseException {
        return getDoubleValue("21");
    }

    @Item(number = "23")
    public Double getGammaRaw(String witsPackage) throws WitsParseException {
        return getDoubleValue("23");
    }

    @Item(number = "24")
    public Double getGammaCorr(String witsPackage) throws WitsParseException {
        return getDoubleValue("24");
    }
}
