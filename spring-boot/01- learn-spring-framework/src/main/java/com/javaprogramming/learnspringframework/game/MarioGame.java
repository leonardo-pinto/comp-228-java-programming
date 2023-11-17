package com.javaprogramming.learnspringframework.game;

public class MarioGame implements GamingConsole {
    public void up(){
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("Down");
    }

    public void left() {
        System.out.println("Go left");
    }

    public void right() {
        System.out.println("Go right");
    }

}
