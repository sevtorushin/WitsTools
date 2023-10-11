package parsers;

public class RecordParser {

    public static String getItem(String record) {
        return record.substring(2, 4);
    }

    public static String getPackageNumber(String record) {
        return record.substring(0, 2);
    }

    public static String getValue(String record){
        return record.substring(4);
    }
}
