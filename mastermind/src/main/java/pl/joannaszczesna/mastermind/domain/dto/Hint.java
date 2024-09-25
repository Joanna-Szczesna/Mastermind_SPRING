/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain.dto;

public record Hint(int colorAndPositionGuessed, int onlyColorGuessed) {
    @Override
    public String toString() {
        return "Hint: [ " + colorAndPositionGuessed +
                " black ] [ " + onlyColorGuessed +
                " white ]";
    }
}
