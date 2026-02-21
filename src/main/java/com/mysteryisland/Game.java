package com.mysteryisland;

import com.mysteryisland.entities.Player;
import com.mysteryisland.ui.Colors;
import com.mysteryisland.utils.Input;
import com.mysteryisland.world.GameMap;

public class Game {
    private Player player;
    private GameMap gameMap;
    private boolean running;
    
    public Game() {
        this.running = true;
    }
    
    public void start() {
        // Начало игры
        System.out.println(Colors.GREEN + "╔════════════════════════════╗" + Colors.RESET);
        System.out.println(Colors.GREEN + "║   ТАИНСТВЕННЫЙ ОСТРОВ   1 часть ║" + Colors.RESET);
        System.out.println(Colors.GREEN + "╚════════════════════════════╝" + Colors.RESET);
        System.out.println("\n🌴 Ты очнулся на берегу...");
        System.out.println("Нужно выжить и выбраться!");
        
        player = new Player();
        gameMap = new GameMap();
        
        // Игровой цикл
        while (running && player.isAlive()) {
            player.update();
            showStatus();
            handleInput();
        }
        
        if (!player.isAlive()) {
            System.out.println(Colors.RED + "\n💀 Ты погиб..." + Colors.RESET);
        }
    }
    
    private void showStatus() {
        System.out.println("\n" + Colors.CYAN + "═══════════════════════════════" + Colors.RESET);
        System.out.println(Colors.YELLOW + "❤️ Здоровье: " + player.getHealth() + "/" + player.getMaxHealth() + 
                         "  🍖 Голод: " + player.getHunger() + "/100" + Colors.RESET);
        System.out.println(Colors.GREEN + "🌍 " + gameMap.getBiomeName(player) + 
                         "  📍 X:" + player.getX() + " Y:" + player.getY() + Colors.RESET);
        System.out.println("\n" + Colors.WHITE + "W:A:S:D - движение | E - собрать | F - атаковать | R - отдых | I - инвентарь | Q - выход" + Colors.RESET);
    }
    
    private void handleInput() {
        char key = Input.getKey();
        
        switch (key) {
            case 'w': gameMap.movePlayer("север", player); break;
            case 's': gameMap.movePlayer("юг", player); break;
            case 'a': gameMap.movePlayer("запад", player); break;
            case 'd': gameMap.movePlayer("восток", player); break;
            case 'e': 
                if (gameMap.hasTrees(player)) {
                    player.getInventory().addItem("wood_piece", 1);
                    System.out.println("🌲 Ты срубил дерево. +1 дерево");
                } else if (gameMap.hasStones(player)) {
                    player.getInventory().addItem("stone_piece", 1);
                    System.out.println("🪨 Ты добыл камень. +1 камень");
                } else if (gameMap.hasBerries(player)) {
                    player.getInventory().addItem("berry", 2);
                    System.out.println("🫐 Ты собрал ягоды. +2 ягоды");
                } else {
                    System.out.println("❌ Здесь нечего собирать");
                }
                player.decreaseHunger(2);
                break;
                
            case 'f':
                if (gameMap.hasAnimals(player)) {
                    if (Math.random() < 0.6) {
                        player.getInventory().addItem("raw_meat", 1);
                        System.out.println("🥩 Ты добыл мясо! +1 сырое мясо");
                    } else {
                        System.out.println("💨 Животное убежало");
                    }
                    player.decreaseHunger(5);
                } else {
                    System.out.println("🦌 Здесь нет животных");
                }
                break;
                
            case 'r':
                player.rest();
                System.out.println("😴 Ты отдыхаешь... +20 здоровья");
                break;
                
            case 'i':
                player.getInventory().show();
                break;
                
            case 'q':
                running = false;
                break;
                
            default:
                break;
        }
    }
}
