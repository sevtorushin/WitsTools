package parsers.splitters;

import exceptions.SplitException;

public class RecordSplitter implements Splitter<String, String>{
    private int packageNumberLength;
    private int itemLength;

    public RecordSplitter(int packageNumberLength, int itemLength) {
        this.packageNumberLength = packageNumberLength;
        this.itemLength = itemLength;
    }

    public RecordSplitter() {
        this.packageNumberLength = 2;
        this.itemLength = 2;
    }

    public String getPackageNumber(String record) {
        return record.substring(0, packageNumberLength);
    }

    public String getItem(String record) {
        return record.substring(packageNumberLength, packageNumberLength + itemLength);
    }

    public String getValue(String record) {
        return record.substring(packageNumberLength + itemLength);
    }

    @Override
    public String[] split(String data) throws SplitException {
        if (data.length() < packageNumberLength + itemLength)
            throw new SplitException("Not enough record arguments: " + data);
        return new String[]{getPackageNumber(data), getItem(data), getValue(data)};
    }

    public int getPackageNumberLength() {
        return packageNumberLength;
    }

    public void setPackageNumberLength(int packageNumberLength) {
        this.packageNumberLength = packageNumberLength;
    }

    public int getItemLength() {
        return itemLength;
    }

    public void setItemLength(int itemLength) {
        this.itemLength = itemLength;
    }
}
