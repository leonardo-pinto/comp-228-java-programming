package com.javaprogramming.learnspringframework.game;

import com.javaprogramming.learnspringframework.game.GameRunner;
import com.javaprogramming.learnspringframework.game.MarioGame;

public class App01GamingBasicJava {
    public static void main(String[] args) {
        MarioGame marioGame = new MarioGame();
//        SuperContraGame superContraGame = new SuperContraGame();
        GameRunner gameRunner = new GameRunner(marioGame);
        gameRunner.run();
    }
}
