package com.javaprogramming.learnspringframework.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GamingConfiguration {

    @Bean
    public GamingConsole game() {
        return new MarioGame();
    }

    @Bean
    public GameRunner runner(GamingConsole game) {
        return new GameRunner(game); // or without parameter new GameRunner(game());
    }
}
