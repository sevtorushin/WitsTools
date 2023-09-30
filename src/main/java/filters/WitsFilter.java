package filters;

public class WitsFilter {

    public boolean isWits(String witsPackage) {
        boolean isWitsHeader = witsPackage.startsWith("&&");
        boolean isWitsFooter = witsPackage.endsWith("!!");

        return isWitsHeader && isWitsFooter;
    }
}
