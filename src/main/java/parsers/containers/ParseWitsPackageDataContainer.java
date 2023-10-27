package parsers.containers;

import parsers.splitters.RecordSplitter;

import java.util.HashMap;
import java.util.Map;

public class ParseWitsPackageDataContainer {
    private Map<String, String[]> storage = new HashMap<>();
    private RecordSplitter recordSplitter;

    public ParseWitsPackageDataContainer(RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
    }

    public ParseWitsPackageDataContainer() {
        this.recordSplitter = new RecordSplitter(2, 2);
    }

    public ParseWitsPackageDataContainer(ParseWitsPackageDataContainer container) {
        this.storage = new HashMap<>(container.getStorage());
        this.recordSplitter = new RecordSplitter(container.getRecordSplitter().getPackageNumberLength(),
                container.getRecordSplitter().getItemLength());
    }

    public void put(String packageNumber, String item, String value) {
        storage.put(item, new String[]{value, packageNumber});
    }

    public void put(String record) {
        String[] tokens = recordSplitter.split(record);
        storage.put(tokens[1], new String[]{tokens[2], tokens[0]});
    }

    public String getValue(String item) {
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

    public RecordSplitter getRecordSplitter() {
        return recordSplitter;
    }

    public void setRecordSplitter(RecordSplitter recordSplitter) {
        this.recordSplitter = recordSplitter;
    }
}
