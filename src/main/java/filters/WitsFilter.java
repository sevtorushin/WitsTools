package filters;

public class WitsFilter implements Filter {

    public String filtrate(String witsPackage) {
        boolean isWitsHeader = witsPackage.startsWith("&&");
        boolean isWitsFooter = witsPackage.endsWith("!!");
        if (isWitsHeader && isWitsFooter)
            return witsPackage;
        else
            return null;
    }
}
