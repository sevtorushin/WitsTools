package parsers;

import parsers.splitters.RecordSplitter;
import validators.RecordValidator;
import validators.TimeBasedRecordValidator;

public class TimeBasedRecordParser extends WitsRecordParser {
    public TimeBasedRecordParser() {
        super(new TimeBasedRecordValidator(), new RecordSplitter(2, 2));
    }
}
