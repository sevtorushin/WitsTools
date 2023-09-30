import configuration.Configuration;
import exceptions.WitsPackageException;
import filters.PackageFilter;
import filters.TimeBasedPackageFilter;
import filters.WitsFilter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import validators.TimeBasedPackageValidator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws WitsPackageException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);
        String pack01 = "&&\n" +
                "0101ANY\r\n" +
                "01020\n" +
                "0105200824\n" +
                "0106115408\r\n" +
                "0108438.517\n" +
                "01101683.51\r\n" +
                "011227.35\n" +
                "012164.1427\n" +
                "!!";
        String pack02 = "&&\n" +
                "0201ANY\n" +
                "02010\n" +
                "0205160223\n" +
                "0206121559\n" +
                "!!";
//        WitsFilter witsFilter = new WitsFilter();
//        System.out.println(witsFilter.isWits(pack02));
//        PackageFilter packageFilter = new PackageFilter("02", "01");
//        System.out.println(packageFilter.filtrate(pack02));
//        TimeBasedPackageFilter filter = new TimeBasedPackageFilter("05", "06", "21", "12");
//        System.out.println(filter.filtrate(pack01));

        TimeBasedPackageValidator validator = new TimeBasedPackageValidator();
        System.out.println(validator.validateValue(pack01));




//        context.close();
    }
}
