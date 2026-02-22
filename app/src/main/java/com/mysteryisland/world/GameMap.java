package com.mysteryisland.world;

import com.mysteryisland.entities.Player;

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
                else if (rand < 0.85) biomes[x][y] = "beach";
                else biomes[x][y] = "swamp";
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
        String biome = biomes[player.getX()][player.getY()];
        switch (biome) {
            case "forest": return "🌲 Лес";
            case "plains": return "🌿 Равнина";
            case "mountain": return "⛰️ Гора";
            case "beach": return "🏖️ Пляж";
            case "swamp": return "🟢 Болото";
            default: return "🌍 Остров";
        }
    }
    
    public boolean hasTrees(Player player) {
        String b = biomes[player.getX()][player.getY()];
        return b.equals("forest") || b.equals("swamp");
    }
    
    public boolean hasStones(Player player) {
        String b = biomes[player.getX()][player.getY()];
        return b.equals("mountain") || b.equals("beach");
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
        String b = biomes[player.getX()][player.getY()];
        return b.equals("beach") || b.equals("swamp");
    }
    
    public String getSurroundings(Player player) {
        StringBuilder sb = new StringBuilder();
        if (hasTrees(player)) sb.append("🌲 ");
        if (hasStones(player)) sb.append("🪨 ");
        if (hasBerries(player)) sb.append("🫐 ");
        if (hasAnimals(player)) sb.append("🦌 ");
        if (hasWater(player)) sb.append("💧 ");
        
        if (sb.length() == 0) return "❌ Ничего нет";
        return sb.toString();
    }
}
