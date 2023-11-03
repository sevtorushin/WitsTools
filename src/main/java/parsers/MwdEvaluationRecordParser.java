package parsers;

import parsers.splitters.RecordSplitter;
import validators.MwdEvaluationRecordValidator;
import validators.TimeBasedRecordValidator;
import validators.ValidatorBuilder;

import java.util.ArrayList;
import java.util.List;

public class MwdEvaluationRecordParser extends WitsRecordParser {
    public MwdEvaluationRecordParser() {
        super(new ValidatorBuilder<>(new ArrayList<>(List.of(new MwdEvaluationRecordValidator()))), new RecordSplitter(2, 2));
    }

    public MwdEvaluationRecordParser(ValidatorBuilder<MwdEvaluationRecordValidator> validatorBuilder) {
        super(validatorBuilder, new RecordSplitter(2, 2));
    }
}
