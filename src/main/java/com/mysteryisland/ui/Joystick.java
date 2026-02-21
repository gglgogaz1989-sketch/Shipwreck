package com.mysteryisland.ui;

import com.mysteryisland.entities.Player;
import com.mysteryisland.utils.Input;
import com.mysteryisland.world.GameMap;

public class Joystick {
    
    public static void move(Player player, GameMap map) {
        while (true) {
            // Показываем текущую позицию
            Colors.println(Colors.CYAN, "\n📍 Твоя позиция: X=" + player.getX() + " Y=" + player.getY());
            
            // Показываем карту вокруг игрока
            showSurroundings(map, player);
            
            // Рисуем джойстик
            drawJoystick();
            
            Colors.println(Colors.YELLOW, "\nКуда идти?");
            Colors.println(Colors.WHITE, "  W - Вверх (север)");
            Colors.println(Colors.WHITE, "  S - Вниз (юг)");
            Colors.println(Colors.WHITE, "  A - Влево (запад)");
            Colors.println(Colors.WHITE, "  D - Вправо (восток)");
            Colors.println(Colors.WHITE, "  Q - Вернуться");
            
            String input = Input.getString("Твой выбор: ").toLowerCase();
            
            if (input.equals("q")) {
                Colors.println(Colors.GREEN, "↩️ Возврат в меню");
                return;
            }
            
            // Запоминаем старую позицию
            int oldX = player.getX();
            int oldY = player.getY();
            
            // Пытаемся переместиться
            boolean moved = map.movePlayer(input, player);
            
            if (moved) {
                // Показываем новый биом
                map.showCurrentLocation(player);
                
                // Шанс найти ресурсы при перемещении
                if (Math.random() < 0.2) {
                    Colors.println(Colors.GREEN, "✨ По пути ты нашёл что-то интересное!");
                    map.exploreArea(player);
                }
            }
        }
    }
    
    private static void showSurroundings(GameMap map, Player player) {
        int px = player.getX();
        int py = player.getY();
        
        Colors.println(Colors.WHITE, "\n🗺️ Окрестности (5x5):");
        
        for (int dy = -2; dy <= 2; dy++) {
            System.out.print("  ");
            for (int dx = -2; dx <= 2; dx++) {
                int x = px + dx;
                int y = py + dy;
                
                if (dx == 0 && dy == 0) {
                    // Игрок
                    Colors.print(Colors.RED, "⛹️");
                } else if (map.isValidPosition(x, y)) {
                    // Отображаем тип местности
                    String terrain = map.getTerrainIcon(x, y);
                    Colors.print(map.getTerrainColor(x, y), terrain);
                } else {
                    // За пределами карты
                    Colors.print(Colors.BLUE, "🌊");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    private static void drawJoystick() {
        Colors.println(Colors.YELLOW, "\n🕹️ ДЖОЙСТИК:");
        System.out.println("       ↑");
        System.out.println("       W");
        System.out.println("   ← A   D →");
        System.out.println("       S");
        System.out.println("       ↓");
        System.out.println("       Q - выход");
    }
}
