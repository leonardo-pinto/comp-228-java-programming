package com.javaprogramming.learnspringframework.game;

public class App01GamingBasicJava {
    public static void main(String[] args) {
        MarioGame marioGame = new MarioGame();
//        SuperContraGame superContraGame = new SuperContraGame();
        GameRunner gameRunner = new GameRunner(marioGame);
        gameRunner.run();
    }
}
