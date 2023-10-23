package parsers.containers;

import parsers.RecordSplitter;

import java.util.HashMap;
import java.util.Map;

public class ParseWitsDataContainer {
    private Map<String, String[]> map = new HashMap<>();
    private RecordSplitter recordSplitter;

    public ParseWitsDataContainer(RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
    }

    public ParseWitsDataContainer() {
        this.recordSplitter = new RecordSplitter(2, 2);
    }

    public void put(String packageNumber, String item, String value) {
        map.put(item, new String[]{value, packageNumber});
    }

    public void put(String record) {
        String[] tokens = recordSplitter.split(record);
        map.put(tokens[1], new String[]{tokens[2], tokens[0]});
    }

    public String getValue(String item) {
        return map.get(item)[0];
    }

    public void clear() {
        map.clear();
    }

    public Map<String, String[]> getMap() {
        return new HashMap<>(map);
    }

    public RecordSplitter getRecordSplitter() {
        return recordSplitter;
    }

    public void setRecordSplitter(RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
    }
}
