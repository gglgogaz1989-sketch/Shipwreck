package com.mysteryisland.world;

import com.mysteryisland.entities.Player;
import com.mysteryisland.ui.Colors;

public class GameMap {
    private static final int MAP_SIZE = 1000;
    private Tile[][] tiles;
    private int smallIslandX, smallIslandY;
    private int bigIslandX, bigIslandY;
    
    public GameMap() {
        this.tiles = new Tile[MAP_SIZE][MAP_SIZE];
        this.smallIslandX = 500;
        this.smallIslandY = 500;
        this.bigIslandX = 700;
        this.bigIslandY = 300;
        generateMap();
    }
    
    private void generateMap() {
        // Сначала всё океан
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                tiles[x][y] = new Tile(TileType.OCEAN, 0);
            }
        }
        
        // Генерируем маленький остров
        generateSmallIsland(smallIslandX, smallIslandY);
        generateBigIsland(bigIslandX, bigIslandY);
    }
    
    private void generateSmallIsland(int centerX, int centerY) {
        int radius = 50;
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int y = centerY - radius; y <= centerY + radius; y++) {
                if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) continue;
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                
                if (distance < radius) {
                    if (distance < 10) {
                        tiles[x][y] = new Tile(TileType.BEACH, 5);
                    } else if (distance < 20) {
                        tiles[x][y] = new Tile(TileType.FOREST, 10);
                    } else if (distance < 35) {
                        tiles[x][y] = new Tile(TileType.PLAINS, 8);
                    } else {
                        tiles[x][y] = new Tile(TileType.STONY, 3);
                    }
                }
            }
        }
    }
    
    private void generateBigIsland(int centerX, int centerY) {
        int radius = 200;
        for (int x = centerX - radius; x <= centerX + radius; x++) {
            for (int y = centerY - radius; y <= centerY + radius; y++) {
                if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) continue;
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));
                
                if (distance < radius) {
                    if (distance < 30) {
                        tiles[x][y] = new Tile(TileType.MOUNTAIN, 50);
                    } else if (distance < 60) {
                        tiles[x][y] = new Tile(TileType.STONY, 30);
                    } else if (distance < 100) {
                        tiles[x][y] = new Tile(TileType.FOREST, 20);
                    } else if (distance < 150) {
                        tiles[x][y] = new Tile(TileType.PLAINS, 10);
                    } else {
                        tiles[x][y] = new Tile(TileType.BEACH, 5);
                    }
                }
            }
        }
    }
    
    public boolean movePlayer(String direction, Player player) {
        int newX = player.getX();
        int newY = player.getY();
        
        switch (direction) {
            case "w": newY--; break;
            case "s": newY++; break;
            case "a": newX--; break;
            case "d": newX++; break;
            default: return false;
        }
        
        if (newX < 0 || newX >= MAP_SIZE || newY < 0 || newY >= MAP_SIZE) {
            Colors.println(Colors.RED, "❌ Край света!");
            return false;
        }
        
        if (tiles[newX][newY].getType() == TileType.OCEAN) {
            Colors.println(Colors.RED, "❌ Там океан!");
            return false;
        }
        
        player.setX(newX);
        player.setY(newY);
        player.decreaseHunger(2);
        return true;
    }
    
    public void setPlayerPosition(int x, int y) {
        // только для начала игры
    }
    
    public void showCurrentLocation(Player player) {
        Tile tile = tiles[player.getX()][player.getY()];
        Colors.println(tile.getType().getColor(), "\n📍 " + tile.getType().getName());
    }
    
    public boolean hasTreesAtPlayer(Player player) {
        Tile tile = tiles[player.getX()][player.getY()];
        return tile.getType() == TileType.FOREST;
    }
    
    public boolean hasStonesAtPlayer(Player player) {
        Tile tile = tiles[player.getX()][player.getY()];
        return tile.getType() == TileType.STONY || tile.getType() == TileType.MOUNTAIN;
    }
    
    public boolean hasAnimalsAtPlayer(Player player) {
        Tile tile = tiles[player.getX()][player.getY()];
        return tile.getType() == TileType.FOREST || tile.getType() == TileType.PLAINS;
    }
    
    public boolean hasWaterAtPlayer(Player player) {
        Tile tile = tiles[player.getX()][player.getY()];
        return tile.getType() == TileType.RIVER || tile.getType() == TileType.BEACH;
    }
    
    public boolean chopTreeAtPlayer(Player player) {
        return hasTreesAtPlayer(player) && Math.random() < 0.8;
    }
    
    public boolean mineStoneAtPlayer(Player player) {
        return hasStonesAtPlayer(player) && Math.random() < 0.7;
    }
    
    public boolean huntAtPlayer(Player player) {
        return hasAnimalsAtPlayer(player) && Math.random() < 0.5;
    }
    
    public void exploreArea(Player player) {
        if (Math.random() < 0.3) {
            player.getInventory().addItem("berry", 1);
            Colors.println(Colors.GREEN, "🌿 Ты нашёл ягоды!");
        }
    }
    
    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE;
    }
    
    public String getTerrainIcon(int x, int y) {
        return tiles[x][y].getType().getIcon();
    }
    
    public String getTerrainColor(int x, int y) {
        return tiles[x][y].getType().getColor();
    }
                       }
