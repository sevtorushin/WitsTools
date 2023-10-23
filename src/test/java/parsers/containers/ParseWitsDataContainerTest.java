package parsers.containers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import parsers.RecordSplitter;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParseWitsDataContainerTest {
    private static ParseWitsDataContainer container;
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
        container = new ParseWitsDataContainer();
    }

    static Stream<Arguments> methodPackNumDataProvider() {
        return Stream.of(
                Arguments.arguments("0101ANY")
        );
    }

    @Test
    void successfulExecutePut() {
        container.put("0101ANY");
        String inputPackNum = "01";
        String inputItem = "01";
        String inputValue = "ANY";
        String packNumInContainer = container.getMap().get(inputItem)[1];
        String itemInContainer = container.getMap().containsKey(inputItem) ? inputItem : null;
        String valueInContainer = container.getMap().get(inputItem)[0];
        Assertions.assertEquals(inputPackNum, packNumInContainer);
        Assertions.assertEquals(inputItem, itemInContainer);
        Assertions.assertEquals(inputValue, valueInContainer);
    }

    @Test
    void throwExceptionPut() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> container.put("01"));
    }

    @Test
    void successfulExecuteOverloadedPut() {
        container.put("01", "05", "231023");
        String inputPackNum = "01";
        String inputItem = "05";
        String inputValue = "231023";
        String packNumInContainer = container.getMap().get(inputItem)[1];
        String itemInContainer = container.getMap().containsKey(inputItem) ? inputItem : null;
        String valueInContainer = container.getMap().get(inputItem)[0];
        Assertions.assertEquals(inputPackNum, packNumInContainer);
        Assertions.assertEquals(inputItem, itemInContainer);
        Assertions.assertEquals(inputValue, valueInContainer);
    }

    @Test
    void getValue() {
        container.put("0106180214");
        Assertions.assertEquals("180214", container.getValue("06"));
    }

    @Test
    void shouldGetNullGetValue() {
        Assertions.assertNull(container.getValue("12"));
    }

    @Test
    void clear() {
        container.put("0114122.54");
        Assertions.assertEquals(1, container.getMap().size());
        container.clear();
        Assertions.assertEquals(0, container.getMap().size());
    }
}