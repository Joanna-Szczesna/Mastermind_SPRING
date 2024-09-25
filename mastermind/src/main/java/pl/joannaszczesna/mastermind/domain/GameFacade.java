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

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicInteger;

public class  GameFacade {
    private static final Logger log = Logger.getLogger(GameFacade.class.getName());
    private final AtomicInteger counter = new AtomicInteger(0);
    private RulesOfMasterMind rules;
    private final CodeGenerator codeGenerator;

    public GameFacade(CodeGenerator generator) {
        codeGenerator = generator;
        rules = new RulesOfMasterMind(codeGenerator.generate());
    }
    public GameResponse checkCode(List<Integer> userCode) {
        log.log(Level.INFO, "Check code counter= {0}", counter.incrementAndGet());
        if (rules.isGameEnd()) {
            throw new IllegalStateException("Game is already finished");
        }
        if (isInvalidUserCode(userCode)) {
            userCode = List.of(0, 0, 0, 0, 0);
        }

        Hint hint = rules.rateAttempt(new Code(userCode));
        State state = rules.getState();
        return new GameResponse(state, hint);
    }

    private boolean isInvalidUserCode(List<Integer> userCode) {
        if (userCode == null ||
                userCode.size() != 5) {
            return true;
        }
        if (isUserCodeOutsideRange(userCode)) {
            return true;
        }
        return false;

    }

    private boolean isUserCodeOutsideRange(List<Integer> userCode) {
        Integer min = 1;
        Integer max = 8;
        Long validElements = userCode.stream()
                .filter(Objects::nonNull)
                .filter(e -> e >= min && e <= max).count();
        return validElements != 5;
    }

    public boolean isGameEnd() {


        return rules.isGameEnd();
    }

    public GameResponse boom() {
        rules.boom();
        return new GameResponse(rules.getState(), new Hint(0, 0));
    }

    public Code showSecretCode() {
        if (isGameEnd()) {
            return rules.showSecretCode();
        }
        throw new IllegalStateException("Game");
    }

    public boolean startNewGame() {
        rules = new RulesOfMasterMind(codeGenerator.generate());
        return true;
    }

    public record GameResponse(State state, Hint hint) {
    }

}
