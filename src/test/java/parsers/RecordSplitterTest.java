package parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class RecordSplitterTest {
    private static RecordSplitter splitter;

    @BeforeAll
    static void initSplitter(){
        splitter = new RecordSplitter();
    }

    static Stream<Arguments> methodPackNumDataProvider() {
        return Stream.of(
                Arguments.arguments("01010", "01"),
                Arguments.arguments("0716345", "07"),
                Arguments.arguments("0112-0.5e-005", "01"),
                Arguments.arguments("0101ANY", "01")
        );
    }

    static Stream<Arguments> methodItemDataProvider() {
        return Stream.of(
                Arguments.arguments("01010", "01"),
                Arguments.arguments("0716345", "16"),
                Arguments.arguments("0112-0.5e-005", "12"),
                Arguments.arguments("0101ANY", "01")
        );
    }

    static Stream<Arguments> methodValueDataProvider() {
        return Stream.of(
                Arguments.arguments("01010", "0"),
                Arguments.arguments("0716345", "345"),
                Arguments.arguments("0112-0.5e-005", "-0.5e-005"),
                Arguments.arguments("0101ANY", "ANY")
        );
    }

    @ParameterizedTest
    @MethodSource("methodPackNumDataProvider")
    void getPackageNumber(String data, String expectedPackNum) {
        Assertions.assertEquals(expectedPackNum, splitter.getPackageNumber(data));
    }

    @ParameterizedTest
    @MethodSource("methodItemDataProvider")
    void getItem(String data, String expectedItem) {
        Assertions.assertEquals(expectedItem, splitter.getItem(data));
    }

    @ParameterizedTest
    @MethodSource("methodValueDataProvider")
    void getValue(String data, String expectedValue) {
        Assertions.assertEquals(expectedValue, splitter.getValue(data));
    }
}