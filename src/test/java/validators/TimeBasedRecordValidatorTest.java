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

class TimeBasedRecordValidatorTest {
    private static TimeBasedRecordValidator recordValidator;

    @BeforeAll
    public static void set(){
        recordValidator = new TimeBasedRecordValidator();
    }

    static Stream<Arguments> methodDataProvider() {
        return Stream.of(
                Arguments.arguments("01010", true),
                Arguments.arguments("0105231130", true),
                Arguments.arguments("01052311332", false),
                Arguments.arguments("0106174500", true),
                Arguments.arguments("0106176500", false),
                Arguments.arguments("011227.3", true),
                Arguments.arguments("0112-0.5", true),
                Arguments.arguments("0112-0.1e-005", true),
                Arguments.arguments("021225", false),
                Arguments.arguments("014625", false),
                Arguments.arguments("0101ANY", true),
                Arguments.arguments("0112", false),
                Arguments.arguments("01", false),
                Arguments.arguments("dfq34r", false),
                Arguments.arguments("01100\r\n", false)
        );
    }

    @ParameterizedTest
    @MethodSource("methodDataProvider")
    void getPackageNumber(String data, boolean isValid) {
        Assertions.assertEquals(isValid, recordValidator.isValid(data));
    }
}