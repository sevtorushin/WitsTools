package parsers;

import parsers.splitters.RecordSplitter;
import validators.SurveyDirectionalRecordValidator;
import validators.TimeBasedRecordValidator;
import validators.ValidatorBuilder;

import java.util.ArrayList;
import java.util.List;

public class SurveyDirectionalRecordParser extends WitsRecordParser {
    public SurveyDirectionalRecordParser() {
        super(new ValidatorBuilder<>(new ArrayList<>(List.of(new SurveyDirectionalRecordValidator()))), new RecordSplitter(2, 2));
    }

    public SurveyDirectionalRecordParser(ValidatorBuilder<SurveyDirectionalRecordValidator> validatorBuilder) {
        super(validatorBuilder, new RecordSplitter(2, 2));
    }
}
