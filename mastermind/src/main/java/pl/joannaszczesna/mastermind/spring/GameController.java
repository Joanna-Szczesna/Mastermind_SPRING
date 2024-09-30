/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.joannaszczesna.mastermind.domain.GameFacade;

import java.util.List;

@RestController
class GameController {
    GameFacade gameFacade;

    @Autowired
    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }

    @PostMapping("/guess-code")
    ResponseEntity<GameFacade.GameResponse> guessCode(@RequestBody List<Integer> userCode) {
        return ResponseEntity.ok(null);
    }
}
