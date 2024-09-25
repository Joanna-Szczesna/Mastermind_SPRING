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
import pl.joannaszczesna.mastermind.domain.dto.Hint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RulesOfMasterMindTest {
    private RulesOfMasterMind rules;
    private final Code correct = new Code(List.of(4, 4, 5, 7, 7));
    private final Code onePositionCorrectSameDigits = new Code(List.of(5, 5, 5, 5, 5));
    private final Code twoPositionCorrect = new Code(List.of(4, 8, 8, 8, 7));
    private final Code incorrect = new Code(List.of(3, 3, 3, 3, 3, 3));
    private final Code threeColorsCorrectPositionsWrong = new Code(List.of(7, 5, 4, 8, 8));
    private final Code onePositionTwoColorsCorrect = new Code(List.of(4, 5, 7, 8, 8));

    @BeforeEach
    void setUp() {
        CodeGenerator generator = new CodeGeneratorMock(correct.planetNumbers());
        final Code generatedCode = generator.generate();
        rules = new RulesOfMasterMind(generatedCode);
    }

    @Nested
    class EndGame {
        @Test
        void whenUserGuessCodeInFirsAttempt_GameIsFinished() {
            rules.rateAttempt(correct);
            boolean isGameFinished = rules.isGameEnd();

            assertTrue(isGameFinished);
        }

        @Test
        void whenUserDoesNotGuessCodeInFirsAttempt_GameIsInProgress() {
            rules.rateAttempt(incorrect);
            boolean isGameOver = rules.isGameEnd();

            assertFalse(isGameOver);
        }

        @Test
        void whenUserReachedMaximumAttempts_GameOver() {
            int possibleAttempts = 10;
            for (int i = 0; i < possibleAttempts; i++) {
                rules.rateAttempt(incorrect);
            }
            boolean isGameOver = rules.isGameEnd();

            assertTrue(isGameOver);
        }

        @Test
        void whenUserExceededMaximumAttempts_GameOver() {
            int oneMoreThanPossibleAttempts = 11;
            for (int i = 0; i < oneMoreThanPossibleAttempts; i++) {
                rules.rateAttempt(incorrect);
            }
            boolean isGameOver = rules.isGameEnd();

            assertTrue(isGameOver);
        }

        @Test
        void whenUserTryMoreThanMaximumByteValue_GameOver() {
            int attempts = Byte.MAX_VALUE + 20;
            for (int i = 0; i < attempts; i++) {
                rules.rateAttempt(incorrect);
            }
            boolean isGameOver = rules.isGameEnd();

            assertTrue(isGameOver);
        }

        @Test
        void whenBeforeGuessCodeIsInvokeIsEnd_returnFalse() {
            boolean isGameOver = rules.isGameEnd();

            assertFalse(isGameOver);
        }
    }

    @Nested
    class State {
        @Test
        void whenUserGuessCode_winStatement() {
            rules.rateAttempt(correct);
            String statement = rules.getState().toString();

            assertEquals("You guessed the secret code! Earth is safe", statement);
        }

        @Test
        void whenUserExceededMaximumAttempts_loseStatement() {
            int oneMoreThanPossibleAttempts = 11;
            for (int i = 0; i < oneMoreThanPossibleAttempts; i++) {
                rules.rateAttempt(incorrect);
            }
            String statement = rules.getState().toString();

            assertEquals("           Game over", statement);
        }

        @Test
        void whenUserStillHaveSomeAttempts_continueStatement() {
            int oneLessThanPossibleAttempts = 9;
            for (int i = 0; i < oneLessThanPossibleAttempts; i++) {
                rules.rateAttempt(incorrect);
            }
            String statement = rules.getState().toString();

            assertEquals("Still in game", statement);
        }
    }

    @Nested
    class HintCheck {
        @Test
        void whenUserNotGuessAnyPartOfCode_hintFieldsRemainsZero() {
            final Hint hint = rules.rateAttempt(incorrect);

            assertEquals(new Hint(0, 0), hint);
        }

        @Test
        void whenUserGuessAllCode_hintColorAndPositionGuessedSameNumberAsPasswordSize() {
            int size = correct.planetNumbers().size();
            final Hint hint = rules.rateAttempt(correct);

            assertEquals(new Hint(size, 0), hint);
        }

        @Test
        void whenUserGuessTwoPositionCode_hintColorAndPositionGuessedIsTwo() {
            final Hint hint = rules.rateAttempt(twoPositionCorrect);

            assertEquals(new Hint(2, 0), hint);
        }

        @Test
        void whenUserGuessThreeColorsCode_hintOnlyColorGuessedIsThree() {
            final Hint hint = rules.rateAttempt(threeColorsCorrectPositionsWrong);

            assertEquals(new Hint(0, 3), hint);
        }

        @Test
        void whenUserGuessOnePositionAndTwoColorsCode_hintColorAndPositionGuessedIsOneOnlyColorGuessedIsTwo() {
            final Hint hint = rules.rateAttempt(onePositionTwoColorsCorrect);

            assertEquals(new Hint(1, 2), hint);
        }

        @Test
        void whenUserProvidedSameDigitsAndGuessOnePosition_hintColorAndPositionGuessedIsOneOnlyColorGuessedIsZero() {
            final Hint hint = rules.rateAttempt(onePositionCorrectSameDigits);

            assertEquals(new Hint(1, 0), hint);
        }
    }

    @Nested
    class HintForUniqueDigitsCode {
        private final Code onePositionCorrectThreeColorsCorrect = new Code(List.of(4, 2, 6, 5, 6)
        );

        @BeforeEach
        void setUp() {
            CodeGenerator generatorMock = new CodeGeneratorMock(List.of(6, 2, 4, 7, 5));
            final Code generatedUniqueCode = generatorMock.generate();
            rules = new RulesOfMasterMind(generatedUniqueCode);
        }

        @Test
        void guessOnePositionAndThreeColorsCode_hintColorAndPositionGuessedIsOneOnlyColorGuessedIsThree() {
            Hint hint = rules.rateAttempt(onePositionCorrectThreeColorsCorrect);
            assertEquals(new Hint(1, 3), hint);
        }
    }
}