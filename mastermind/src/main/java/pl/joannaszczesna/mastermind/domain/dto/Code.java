/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain.dto;

import java.util.List;

public record Code(List<Integer> planetNumbers) {
    @Override
    public String toString() {
        return planetNumbers.toString();
    }
}
