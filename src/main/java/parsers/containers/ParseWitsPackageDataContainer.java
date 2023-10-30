package parsers.containers;

import exceptions.WitsParseException;
import exceptions.WitsRecordParseException;
import parsers.WitsRecordParser;

import java.util.HashMap;
import java.util.Map;

public class ParseWitsPackageDataContainer {
    private Map<String, String[]> storage = new HashMap<>();
    private WitsRecordParser recordParser;

    public ParseWitsPackageDataContainer(WitsRecordParser recordParser) {
        this.recordParser = recordParser;
    }


    public ParseWitsPackageDataContainer(ParseWitsPackageDataContainer container) {
        this.storage = new HashMap<>(container.getStorage());
        this.recordParser = container.getRecordParser();
    }

    public void put(String packageNumber, String item, String value) throws WitsRecordParseException {
        put(String.join(packageNumber, item, value));
    }

    public void put(String record) throws WitsRecordParseException {
        recordParser = recordParser.parse(record);
        storage.put(recordParser.getItem(), new String[]{recordParser.getValue(), recordParser.getPackageNumber()});
    }

    public String getValue(String item) throws WitsParseException {
        if (storage.isEmpty())
            throw new WitsParseException("Parse exception. Data is missing. Inner container is empty");
        String[] arr = storage.get(item);
        if (arr == null)
            return null;
        else
            return storage.get(item)[0];
    }

    public void clear() {
        storage.clear();
    }

    public Map<String, String[]> getStorage() {
        return new HashMap<>(storage);
    }

    public WitsRecordParser getRecordParser() {
        return recordParser;
    }

    public void setRecordParser(WitsRecordParser recordParser) {
        this.recordParser = recordParser;
    }
}
