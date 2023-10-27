package parsers.containers;

import exceptions.WitsRecordParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import parsers.TimeBasedRecordParser;

import java.util.stream.Stream;

class ParseWitsPackageDataContainerTest {
    private static ParseWitsPackageDataContainer container;
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
    static void setContainer(){
        container = new ParseWitsPackageDataContainer(new TimeBasedRecordParser());
    }

    static Stream<Arguments> methodPackNumDataProvider() {
        return Stream.of(
                Arguments.arguments("0101ANY")
        );
    }

    @Test
    void successfulExecutePut() throws WitsRecordParseException {
        container.put("0101ANY");
        String inputPackNum = "01";
        String inputItem = "01";
        String inputValue = "ANY";
        String packNumInContainer = container.getStorage().get(inputItem)[1];
        String itemInContainer = container.getStorage().containsKey(inputItem) ? inputItem : null;
        String valueInContainer = container.getStorage().get(inputItem)[0];
        Assertions.assertEquals(inputPackNum, packNumInContainer);
        Assertions.assertEquals(inputItem, itemInContainer);
        Assertions.assertEquals(inputValue, valueInContainer);
    }

    @Test
    void throwExceptionPut() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> container.put("01"));
    }

    @Test
    void successfulExecuteOverloadedPut() throws WitsRecordParseException {
        container.put("01", "05", "231023");
        String inputPackNum = "01";
        String inputItem = "05";
        String inputValue = "231023";
        String packNumInContainer = container.getStorage().get(inputItem)[1];
        String itemInContainer = container.getStorage().containsKey(inputItem) ? inputItem : null;
        String valueInContainer = container.getStorage().get(inputItem)[0];
        Assertions.assertEquals(inputPackNum, packNumInContainer);
        Assertions.assertEquals(inputItem, itemInContainer);
        Assertions.assertEquals(inputValue, valueInContainer);
    }

    @Test
    void getValue() throws WitsRecordParseException {
        container.put("0106180214");
        Assertions.assertEquals("180214", container.getValue("06"));
    }

    @Test
    void shouldGetNullGetValue() {
        Assertions.assertNull(container.getValue("12"));
    }

    @Test
    void clear() throws WitsRecordParseException {
        container.put("0114122.54");
        Assertions.assertEquals(1, container.getStorage().size());
        container.clear();
        Assertions.assertEquals(0, container.getStorage().size());
    }
}