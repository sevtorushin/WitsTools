package parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class WitsPackageParserTest {
    private static WitsPackageParser witsPackageParser;
    private static final String witsPackage = "&&\n" +
            "01018800\n" +
            "01020\n" +
            "010301\n" +
            "01040\n" +
            "0105200824\n" +
            "0106115408\n" +
            "010702\n +" +
            "!!";

    @BeforeAll
    static void setWitsPackageParser(){
        witsPackageParser = new WitsPackageParser();
    }

    static Stream<Arguments> methodPackNumDataProvider() {
        return Stream.of(
                Arguments.arguments(witsPackage, "01", "8800"),
                Arguments.arguments(witsPackage, "02", "0"),
                Arguments.arguments(witsPackage, "03", "01"),
                Arguments.arguments(witsPackage, "04", "0"),
                Arguments.arguments(witsPackage, "05", "200824"),
                Arguments.arguments(witsPackage, "06", "115408"),
                Arguments.arguments(witsPackage, "07", "02")
        );
    }

    @ParameterizedTest
    @MethodSource("methodPackNumDataProvider")
    void getValue(String witsPackage, String item, String expectedValue) {
        Assertions.assertEquals(expectedValue, witsPackageParser.getValue(witsPackage, item));
    }

    @Test
    void getWellId() {
        Assertions.assertEquals("8800", witsPackageParser.getWellId(witsPackage));
    }

    @Test
    void getHoleSectNo() {
        Assertions.assertEquals("0", witsPackageParser.getHoleSectNo(witsPackage));
    }

    @Test
    void getRecordId() {
        Assertions.assertEquals("01", witsPackageParser.getRecordId(witsPackage));
    }

    @Test
    void getSeqId() {
        Assertions.assertEquals("0", witsPackageParser.getSeqId(witsPackage));
    }

    @Test
    void getDate() {
        LocalDate date = LocalDate.parse("200824", DateTimeFormatter.ofPattern("yyMMdd"));
        Assertions.assertEquals(date, witsPackageParser.getDate(witsPackage));
    }

    @Test
    void getTime() {
        LocalTime time = LocalTime.parse("115408", DateTimeFormatter.ofPattern("HHmmss"));
        Assertions.assertEquals(time, witsPackageParser.getTime(witsPackage));
    }

    @Test
    void getActivCode() {
        Assertions.assertEquals("02", witsPackageParser.getActivCode(witsPackage));
    }
}