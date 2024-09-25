/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain;

import pl.joannaszczesna.mastermind.domain.dto.Code;

public interface CodeGenerator {
    Code generate();
}
