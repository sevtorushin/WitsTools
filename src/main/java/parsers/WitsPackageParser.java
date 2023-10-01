package parsers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WitsPackageParser {

    public byte[] getLineFeed(){
        return null;
    }

    public String getValue(String witsPackage, String item){
        String[] tokens = witsPackage.split("\r?\n|\r");
        String tempItem;
        for (int i = 1; i < tokens.length-1; i++) {
            tempItem = tokens[i].substring(2,4);
            if (tempItem.equals(item))
                return tokens[i].substring(4);
        }
        return null;
    }

    public LocalDate getDate(String witsPackage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        return LocalDate.parse(getValue(witsPackage, "05"),formatter);
    }

    public LocalTime getTime(String witsPackage){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
        return LocalTime.parse(getValue(witsPackage, "06"),formatter);
    }
}
