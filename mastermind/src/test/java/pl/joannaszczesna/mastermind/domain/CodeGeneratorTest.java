/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.joannaszczesna.mastermind.domain.dto.Code;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    private CodeGenerator generator;

    @Nested
    class DefaultConstructor {
        @BeforeEach
        void setUp() {
            generator = new RandomCodeGenerator();
        }

        @Test
        void generatedCode_IsNotNull() {
            Code code = generator.generate();

            assertNotNull(code);
        }

        @Test
        void lengthOfGeneratedCode_betweenFiveAndSeven() {
            int codeSize = generator.generate().planetNumbers().size();
            int min = 5;
            int max = 7;
            boolean moreOrEqualThanMinimum = codeSize >= min;
            boolean lessOrEqualThanMaximum = codeSize <= max;

            assertTrue(moreOrEqualThanMinimum);
            assertTrue(lessOrEqualThanMaximum);
        }

        @Test
        void generatedCodeHasNumbers_betweenOneAndEight() {
            Code code = generator.generate();
            int min = 1;
            int max = 8;
            boolean isAllNumbersBetweenOneAndEight =
                    code.planetNumbers().stream().allMatch(num -> (num >= min && num <= max));

            assertTrue(isAllNumbersBetweenOneAndEight);
        }

        @Test
        void generatedCodes_notRepeatMoreThanTwiceOutOfHundredTimes() {
            int hundred = 100;
            List<Code> codesGroup = IntStream.range(0, 100)
                    .mapToObj(n -> generator.generate()).toList();

            int distinctCodesNum = (int) codesGroup.stream().distinct().count();
            int sameCodesNum = hundred - distinctCodesNum;
            boolean noMoreSameCodesThanTwice = sameCodesNum <= 2;

            assertTrue(noMoreSameCodesThanTwice);
        }

        @Test
        void defaultCodeLength_isFive() {
            int codeLength = generator.generate().planetNumbers().size();

            assertEquals(5, codeLength);
        }
    }
}
