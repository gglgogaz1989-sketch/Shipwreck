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
        }
        
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            System.out.println("❌ Край света!");
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
        String biome = biomes[player.getX()][player.getY()];
        return biome.equals("forest");
    }
    
    public boolean hasStones(Player player) {
        String biome = biomes[player.getX()][player.getY()];
        return biome.equals("mountain");
    }
    
    public boolean hasBerries(Player player) {
        String biome = biomes[player.getX()][player.getY()];
        return biome.equals("plains") || biome.equals("forest");
    }
    
    public boolean hasAnimals(Player player) {
        String biome = biomes[player.getX()][player.getY()];
        return biome.equals("forest") || biome.equals("plains");
    }
    
    public boolean hasWater(Player player) {
        String biome = biomes[player.getX()][player.getY()];
        return biome.equals("beach");
    }
}
