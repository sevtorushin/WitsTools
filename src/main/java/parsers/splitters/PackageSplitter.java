package parsers.splitters;

public class PackageSplitter implements Splitter<String, String>{
    private String lineFeedPattern;

    public PackageSplitter(String lineFeedPattern) {
        this.lineFeedPattern = lineFeedPattern;
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
