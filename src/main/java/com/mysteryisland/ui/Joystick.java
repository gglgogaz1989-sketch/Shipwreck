package com.mysteryisland.ui;

import com.mysteryisland.entities.Player;
import com.mysteryisland.utils.Input;
import com.mysteryisland.world.GameMap;

public class Joystick {
    
    public static void move(Player player, GameMap map) {
        while (true) {
            // Показываем джойстик
            System.out.println(Colors.YELLOW + "\n🕹️ ДЖОЙСТИК:" + Colors.RESET);
            System.out.println("   ┌─────┐");
            System.out.println("   │  ↑  │");
            System.out.println("   │ W   │");
            System.out.println("┌─────┐─────┐");
            System.out.println("│ ← A │ S D → │");
            System.out.println("└─────┴─────┘");
            System.out.println("   │  ↓  │");
            System.out.println("   └─────┘");
            System.out.println("\n📱 Нажми:");
            System.out.println("  8 - Вверх (север)");
            System.out.println("  2 - Вниз (юг)");
            System.out.println("  4 - Влево (запад)");
            System.out.println("  6 - Вправо (восток)");
            System.out.println("  0 - Назад");
            
            String input = Input.getString("👉 ");
            
            if (input.equals("0")) {
                System.out.println(Colors.GREEN + "↩️ Возврат" + Colors.RESET);
                break;
            }
            
            boolean moved = false;
            
            switch (input) {
                case "8":
                case "w":
                case "W":
                    moved = map.movePlayer("север", player);
                    break;
                case "2":
                case "s":
                case "S":
                    moved = map.movePlayer("юг", player);
                    break;
                case "4":
                case "a":
                case "A":
                    moved = map.movePlayer("запад", player);
                    break;
                case "6":
                case "d":
                case "D":
                    moved = map.movePlayer("восток", player);
                    break;
                default:
                    System.out.println(Colors.RED + "❌ Нажми 8,2,4,6 или 0" + Colors.RESET);
                    continue;
            }
            
            if (moved) {
                System.out.println(Colors.GREEN + "🚶 Ты переместился" + Colors.RESET);
                map.showCurrentLocation(player);
            } else {
                System.out.println(Colors.RED + "❌ Туда нельзя" + Colors.RESET);
            }
        }
    }
}
