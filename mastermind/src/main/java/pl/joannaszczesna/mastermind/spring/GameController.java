/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.joannaszczesna.mastermind.domain.GameFacade;

@RestController
class GameController {
    GameFacade gameFacade;

    @Autowired
    public GameController(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }
}
