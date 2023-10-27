package parsers;

import exceptions.WitsRecordParseException;
import parsers.containers.ParseWitsRecordDataContainer;
import parsers.splitters.RecordSplitter;
import validators.RecordValidator;

public abstract class WitsRecordParser implements Parser<WitsRecordParser, String> {
    private ParseWitsRecordDataContainer container;
    private RecordValidator recordValidator;

    public WitsRecordParser(RecordValidator recordValidator, RecordSplitter recordSplitter) {
        container = new ParseWitsRecordDataContainer(recordSplitter);
        this.recordValidator = recordValidator;
    }

    @Override
    public WitsRecordParser parse(String record) throws WitsRecordParseException {
        if (!recordValidator.isValid(record))
            throw new WitsRecordParseException("Invalid record: " + record);
        container.put(record);
        return null;
    }

    public String getPackageNumber(){
        return container.getPackageNumber();
    }

    public String getItem(){
        return container.getItem();
    }

    public String getValue(){
        return container.getValue();
    }
}
