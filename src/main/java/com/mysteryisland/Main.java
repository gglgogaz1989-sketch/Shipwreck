package com.mysteryisland;

import com.mysteryisland.ui.UI;

public class Main {
    public static void main(String[] args) {
        UI.clearScreen();
        UI.printTitle();
        
        Game game = new Game();
        game.start();
    }
}
