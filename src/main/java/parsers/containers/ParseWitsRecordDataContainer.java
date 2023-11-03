package parsers.containers;

import exceptions.SplitException;
import exceptions.WitsRecordParseException;
import parsers.splitters.RecordSplitter;

import java.util.Arrays;

public class ParseWitsRecordDataContainer {
    private String[] storage = new String[3];
    private RecordSplitter recordSplitter;

    public ParseWitsRecordDataContainer(RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
    }

    public ParseWitsRecordDataContainer() {
        this.recordSplitter = new RecordSplitter(2, 2);
    }

    public void put(String record) throws WitsRecordParseException {
        String[] tokens;
        try {
            tokens = recordSplitter.split(record);
        } catch (SplitException e) {
            throw new WitsRecordParseException(e.getMessage());
        }
        storage[0] = tokens[0];
        storage[1] = tokens[1];
        storage[2] = tokens[2];
    }

    public String getPackageNumber(){
        return storage[0];
    }

    public String getItem(){
        return storage[1];
    }

    public String getValue(){
        return storage[2];
    }

    public void clear() {
        Arrays.fill(storage, null);
    }
}
