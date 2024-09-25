/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain;

import pl.joannaszczesna.mastermind.domain.dto.Code;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class RandomCodeGenerator implements CodeGenerator{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 8;
    private final byte codeLength;

    public RandomCodeGenerator() {
        this.codeLength = 5;
    }

    public RandomCodeGenerator(byte codeLength) {
        this.codeLength = codeLength;
    }

    @Override
    public Code generate() {
        return new Code(IntStream.range(0, codeLength)
                .mapToObj(n -> ThreadLocalRandom.current()
                .nextInt(MIN_NUMBER, MAX_NUMBER + 1))
                .toList());
    }
}
