package parsers;

public class PackageSplitter implements Splitter<String, String>{
    private String lineFeedPattern;

    public PackageSplitter(String lineFeedPattern) {
        this.lineFeedPattern = lineFeedPattern;
    }

    public PackageSplitter() {
        this.lineFeedPattern = "\\r?\\n|\\r";
    }

    @Override
    public String[] split(String data) {
        return data.split(lineFeedPattern);
    }

    public String getLineFeedPattern() {
        return lineFeedPattern;
    }

    public void setLineFeedPattern(String lineFeedPattern) {
        this.lineFeedPattern = lineFeedPattern;
    }
}
