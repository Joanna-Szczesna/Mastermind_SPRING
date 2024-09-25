/*
 *  Copyright (c) 2024 Joanna Szczesna
 *  joannaszczesna.pl
 * All rights reserved
 *
 */

package pl.joannaszczesna.mastermind.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.joannaszczesna.mastermind.domain.GameFacade;
import pl.joannaszczesna.mastermind.domain.RandomCodeGenerator;

@Configuration
public class GameConfiguration {
    @Bean
//    @SessionScope
    public GameFacade getFacade() {
        return new GameFacade(new RandomCodeGenerator());
    }
}
