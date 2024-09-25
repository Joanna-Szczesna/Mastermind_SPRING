/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.domain;

import pl.joannaszczesna.mastermind.domain.dto.Code;
import pl.joannaszczesna.mastermind.domain.dto.Hint;
import pl.joannaszczesna.mastermind.domain.dto.State;

import java.util.ArrayList;
import java.util.List;

public class RulesOfMasterMind {
    private final Code secretCode;
    private static final byte MAXIMUM_ATTEMPTS = 10;
    private byte leftAttempts;
    private final List<Code> userCodes = new ArrayList<>();

    public RulesOfMasterMind(Code generatedCode) {
        this.secretCode = generatedCode;
        this.leftAttempts = MAXIMUM_ATTEMPTS;
    }

    private boolean hasUserGuessedCode() {
        Code latestCode = userCodes.stream()
                .reduce((current, next) -> next).orElse(new Code(List.of(0, 0)));

        return latestCode.equals(secretCode);
    }

    private boolean anyAttemptLeft() {
        return leftAttempts > 0;
    }

    boolean isGameEnd() {
        return (hasUserGuessedCode() || !anyAttemptLeft());
    }

    State getState() {
        if (hasUserGuessedCode()) {
            return State.WIN;
        }
        if (anyAttemptLeft()) {
            return State.CONTINUE;
        }
        return State.LOSE;
    }

    Hint rateAttempt(Code userCode) {
        decreaseLeftAttempts();
        collectUserCode(userCode);

        return getHint(userCode);
    }

    private Hint getHint(Code userCode) {
        List<Integer> guessedColorAndPositionNumbers = getGuessedNumbersCorrectPositionList(userCode);
        int colorAndPositionGuessed = guessedColorAndPositionNumbers.size();
        int onlyColorGuessed = getOnlyColorGuessedQuantity(userCode.planetNumbers(), guessedColorAndPositionNumbers);

        return new Hint(colorAndPositionGuessed, onlyColorGuessed);
    }

    private void decreaseLeftAttempts() {
        leftAttempts = !anyAttemptLeft() ? 0 : --leftAttempts;
    }

    private void collectUserCode(Code userCode) {
        userCodes.add(userCode);
    }

    private int getOnlyColorGuessedQuantity(List<Integer> userCode, List<Integer> guessedColorAndPositionNumbers) {
        List<Integer> leftElementsInUserCode = new ArrayList<>(userCode);
        List<Integer> secretCodeWithReplacements = new ArrayList<>(secretCode.planetNumbers());
        for (Integer guessedNum : guessedColorAndPositionNumbers) {
            leftElementsInUserCode.remove(guessedNum);
            setRepeatedNumberToZero(secretCodeWithReplacements, secretCodeWithReplacements.indexOf(guessedNum));
        }
        int onlyColor = 0;
        for (Integer num : leftElementsInUserCode) {
            if (secretCodeWithReplacements.contains(num)) {
                ++onlyColor;
                setRepeatedNumberToZero(secretCodeWithReplacements, secretCodeWithReplacements.indexOf(num));
            }
        }

        return onlyColor;
    }

    private void setRepeatedNumberToZero(List<Integer> secretCodeWithChanges, int indexToReplace) {
        secretCodeWithChanges.set(indexToReplace, 0);

    }

    private List<Integer> getGuessedNumbersCorrectPositionList(Code userCode) {
        List<Integer> guessedNumbers = new ArrayList<>();

        for (int i = 0; i < secretCode.planetNumbers().size(); i++) {
            if (userCode.planetNumbers().get(i).equals(secretCode.planetNumbers().get(i))) {
                guessedNumbers.add(userCode.planetNumbers().get(i));
            }
        }
        return guessedNumbers;
    }

    Code showSecretCode() {
        return this.secretCode;
    }

    void boom() {
        leftAttempts = 0;
    }
}
