/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain;

import pl.joannaszczesna.mastermind.domain.dto.Code;

import java.util.List;

public class CodeGeneratorMock implements CodeGenerator {
    private final List<Integer> codeList;

    public CodeGeneratorMock(List<Integer> code) {
        codeList = code;
    }

    @Override
    public Code generate() {
        return new Code(codeList);
    }
}
