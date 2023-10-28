package parsers;

import parsers.splitters.RecordSplitter;
import validators.TimeBasedRecordValidator;
import validators.ValidatorBuilder;

import java.util.List;

public class TimeBasedRecordParser extends WitsRecordParser {
    public TimeBasedRecordParser() {
        super(new ValidatorBuilder<>(List.of(new TimeBasedRecordValidator())), new RecordSplitter(2, 2));
    }

    public TimeBasedRecordParser(ValidatorBuilder<TimeBasedRecordValidator> validatorBuilder) {
        super(validatorBuilder, new RecordSplitter(2, 2));
    }
}
