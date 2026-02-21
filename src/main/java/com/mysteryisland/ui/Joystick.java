package com.mysteryisland.ui;

import com.mysteryisland.entities.Player;
import com.mysteryisland.utils.Input;
import com.mysteryisland.world.GameMap;

public class Joystick {
    
    public static void move(Player player, GameMap map) {
        System.out.println(Colors.YELLOW + "\n🕹️ ДЖОЙСТИК: W/A/S/D - движение, Q - выход" + Colors.RESET);
        
        String input = Input.getString("> ").toLowerCase();
        
        if (input.equals("q")) return;
        
        if (input.equals("w")) map.movePlayer("север", player);
        else if (input.equals("s")) map.movePlayer("юг", player);
        else if (input.equals("a")) map.movePlayer("запад", player);
        else if (input.equals("d")) map.movePlayer("восток", player);
    }
}
