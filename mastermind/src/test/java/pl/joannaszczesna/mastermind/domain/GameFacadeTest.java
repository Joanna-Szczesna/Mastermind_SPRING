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
import pl.joannaszczesna.mastermind.domain.dto.State;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameFacadeTest {
    private final List<Integer> correctCode = List.of(1, 1, 1, 1, 1);
    private GameFacade gameFacade;
    private static final List ONE_DIGIT_OUTSIDE_RANGE_CODE = List.of(1,1,1,1,0);

    @BeforeEach
    void setUp() {
        gameFacade = new GameFacade(() -> new Code(correctCode));
    }

    @Nested
    class CheckCode {
        @Test
        void userProvideCode_returnGameStateAndHint() {
            List<Integer> userCode = List.of(9, 9, 9, 9, 9);
            GameFacade.GameResponse gameResponse = new GameFacade.GameResponse(
                    State.CONTINUE, new Hint(0, 0));

            assertEquals(gameFacade.checkCode(userCode), gameResponse);
        }

        @Test
        void afterFirstFailAttempt_gameShouldNotBeOver() {
            List<Integer> userCode = List.of(9, 9, 9, 9, 9);
            gameFacade.checkCode(userCode);
            boolean isEnd = gameFacade.isGameEnd();

            assertFalse(isEnd);
        }

        @Test
        void whenGameIsEnd_cannotCheckCodeMoreTime() {
            List<Integer> userCode = correctCode;
            gameFacade.checkCode(userCode);
            boolean isEnd = gameFacade.isGameEnd();

            assertTrue(isEnd);
            assertThrows(IllegalStateException.class, () -> gameFacade.checkCode(userCode));
        }

        @Test
        void nullInputNotAllowed_userCodeIsInitializedWithZeros() {
            List<Integer> userCode = null;
            Hint gameResponseHint = gameFacade.checkCode(userCode).hint();
            Hint expectedHint = new Hint(0,0);

            assertEquals(expectedHint, gameResponseHint);
        }
        @Test
        void atLeastOneDigitOutSideValidRange_userCodeIsInitializedWithZeros() {
            List<Integer> userCode = ONE_DIGIT_OUTSIDE_RANGE_CODE;
            Hint gameResponseHint = gameFacade.checkCode(userCode).hint();
            Hint expectedHint = new Hint(0,0);

            assertEquals(expectedHint, gameResponseHint);
        }

        @Test
        void userCodeContainsNull_userCodeIsInitializedWithZeros() {
            List<Integer> userCode = new ArrayList<>(List.of(1,1,1,1));
            userCode.add(null);

            Hint gameResponseHint = gameFacade.checkCode(userCode).hint();
            Hint expectedHint = new Hint(0,0);

            assertEquals(expectedHint, gameResponseHint);
        }
    }

    @Nested
    class SecretCode {
        @Test
        void whenGameIsInProgress_secretCodeCanNotBeShown() {
            List<Integer> userCode = List.of(1, 2, 3, 4, 5);
            gameFacade.checkCode(userCode);

            assertThrows(IllegalStateException.class, () -> gameFacade.showSecretCode());
        }

        @Test
        void whenGameIsEnd_canCallShowSecretCode() {
            List<Integer> userCode = correctCode;
            GameFacade.GameResponse gameResponse = gameFacade.checkCode(userCode);
            System.out.println(gameResponse.state());
            Code secretCode = gameFacade.showSecretCode();

            assertEquals(secretCode, new Code(correctCode));
        }
    }

    @Nested
    class BoomAction {
        @Test
        void shouldOverGame() {
            gameFacade.boom();
            boolean isEnd = gameFacade.isGameEnd();

            assertTrue(isEnd);
        }
    }
}