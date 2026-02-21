package com.mysteryisland.world;

import com.mysteryisland.entities.Player;
import com.mysteryisland.ui.Colors;

public class GameMap {
    private static final int SIZE = 1000;
    private String[][] biomes;
    
    public GameMap() {
        biomes = new String[SIZE][SIZE];
        generateMap();
    }
    
    private void generateMap() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                double rand = Math.random();
                if (rand < 0.3) biomes[x][y] = "forest";
                else if (rand < 0.5) biomes[x][y] = "plains";
                else if (rand < 0.7) biomes[x][y] = "mountain";
                else biomes[x][y] = "beach";
            }
        }
    }
    
    public boolean movePlayer(String direction, Player player) {
        int x = player.getX();
        int y = player.getY();
        
        switch (direction) {
            case "север": y--; break;
            case "юг": y++; break;
            case "запад": x--; break;
            case "восток": x++; break;
            default: return false;
        }
        
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        
        player.setX(x);
        player.setY(y);
        return true;
    }
    
    public String getBiomeName(Player player) {
        return biomes[player.getX()][player.getY()];
    }
    
    public boolean hasTrees(Player player) {
        return biomes[player.getX()][player.getY()].equals("forest");
    }
    
    public boolean hasStones(Player player) {
        return biomes[player.getX()][player.getY()].equals("mountain");
    }
    
    public boolean hasBerries(Player player) {
        String b = biomes[player.getX()][player.getY()];
        return b.equals("plains") || b.equals("forest");
    }
    
    public boolean hasAnimals(Player player) {
        String b = biomes[player.getX()][player.getY()];
        return b.equals("forest") || b.equals("plains");
    }
    
    public boolean hasWater(Player player) {
        return biomes[player.getX()][player.getY()].equals("beach");
    }
    
    // Методы для Joystick
    public void showCurrentLocation(Player player) {
        System.out.println(Colors.GREEN + "📍 Ты на координатах X=" + player.getX() + " Y=" + player.getY() + Colors.RESET);
    }
    
    public void exploreArea(Player player) {
        // Находим случайный ресурс
        if (Math.random() < 0.3) {
            player.getInventory().addItem("berry", 1);
            System.out.println(Colors.GREEN + "🌿 Ты нашёл ягоды!" + Colors.RESET);
        }
    }
    
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
    
    public String getTerrainIcon(int x, int y) {
        String biome = biomes[x][y];
        switch (biome) {
            case "forest": return "🌲";
            case "plains": return "🌿";
            case "mountain": return "⛰️";
            case "beach": return "🏖️";
            default: return "⬜";
        }
    }
    
    public String getTerrainColor(int x, int y) {
        String biome = biomes[x][y];
        switch (biome) {
            case "forest": return Colors.GREEN;
            case "plains": return Colors.GREEN;
            case "mountain": return Colors.WHITE;
            case "beach": return Colors.YELLOW;
            default: return Colors.WHITE;
        }
    }
}
