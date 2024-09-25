/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain.dto;

public enum State {
    WIN {
        @Override
        public final String toString() {
            return "You guessed the secret code! Earth is safe";
        }
    },
    CONTINUE {
        @Override
        public final String toString() {
            return "Still in game";
        }
    },
    LOSE {
        @Override
        public final String toString() {
            return "           Game over";
        }
    }
}
