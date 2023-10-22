package validators;

import descriptions.TimeBasedDescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RecordValidatorTest {

    private static String recordNumber;
    private static Set<String> itemSet;

    @BeforeAll
    public static void set(){
        recordNumber = TimeBasedDescription.WELL_IDENTIFIER.getPackageNumber();
        itemSet = Arrays.stream(TimeBasedDescription.values()).map(TimeBasedDescription::getItem).collect(Collectors.toSet());
    }

    static Stream<Arguments> methodDataProvider() {
        return Stream.of(
                Arguments.arguments("01010", true, recordNumber, itemSet),
                Arguments.arguments("0105231130", true, recordNumber, itemSet),
                Arguments.arguments("01052311332", false, recordNumber, itemSet),
                Arguments.arguments("0106174500", true, recordNumber, itemSet),
                Arguments.arguments("0106176500", false, recordNumber, itemSet),
                Arguments.arguments("011227.3", true, recordNumber, itemSet),
                Arguments.arguments("0112-0.5", true, recordNumber, itemSet),
                Arguments.arguments("0112-0.1e-005", false, recordNumber, itemSet),
                Arguments.arguments("021225", false, recordNumber, itemSet),
                Arguments.arguments("014625", false, recordNumber, itemSet),
                Arguments.arguments("0101ANY", true, recordNumber, itemSet),
                Arguments.arguments("0112", false, recordNumber, itemSet),
                Arguments.arguments("dfq34r", false, recordNumber, itemSet),
                Arguments.arguments("01100\r\n", false, recordNumber, itemSet)
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataProvider")
    void getPackageNumber(String data, boolean isValid, String packageNumber, Set<String> itemSet) {
        TimeBasedPackageValidator recordValidator = new TimeBasedPackageValidator();
        Assertions.assertEquals(isValid, recordValidator.isValid(data));
    }
}