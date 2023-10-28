package parsers;

import exceptions.WitsRecordParseException;
import parsers.containers.ParseWitsRecordDataContainer;
import parsers.splitters.RecordSplitter;
import validators.RecordValidator;
import validators.ValidatorBuilder;

public abstract class WitsRecordParser implements Parser<WitsRecordParser, String> {
    private final ParseWitsRecordDataContainer container;
    private final ValidatorBuilder<? extends RecordValidator> recordValidators;

    public WitsRecordParser(ValidatorBuilder<? extends RecordValidator> recordValidators, RecordSplitter recordSplitter) {
        container = new ParseWitsRecordDataContainer(recordSplitter);
        this.recordValidators = recordValidators;
    }

    @Override
    public WitsRecordParser parse(String record) throws WitsRecordParseException {
        if (!recordValidators.isValid(record))
            throw new WitsRecordParseException("Invalid record: " + record);
        container.clear();
        container.put(record);
        return this;
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
