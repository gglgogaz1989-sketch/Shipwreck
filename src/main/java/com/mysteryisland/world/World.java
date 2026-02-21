package com.mysteryisland.world;

import com.mysteryisland.entities.Player;
import com.mysteryisland.items.Inventory;
import com.mysteryisland.ui.Colors;

public class GameMap {
    private static final int MAP_SIZE = 1000;
    private Tile[][] tiles;
    private int playerX, playerY;
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
        // Генерируем океан везде
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                tiles[x][y] = new Tile(TileType.OCEAN, 0);
            }
        }
        
        // Генерируем маленький остров (500,500) - место спавна
        generateSmallIsland(smallIslandX, smallIslandY);
        
        // Генерируем большой остров с горой (700,300)
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
                        // Центр острова - пляж (спавн)
                        tiles[x][y] = new Tile(TileType.BEACH, 5);
                    } else if (distance < 20) {
                        // Пальмы и кусты
                        tiles[x][y] = new Tile(TileType.FOREST, 10);
                    } else if (distance < 35) {
                        // Трава и ресурсы
                        tiles[x][y] = new Tile(TileType.PLAINS, 8);
                    } else {
                        // Каменистый берег
                        tiles[x][y] = new Tile(TileType.STONY, 3);
                    }
                }
            }
        }
        
        // Добавляем пальмы (деревья) на маленьком острове
        for (int i = 0; i < 5; i++) {
            int treeX = centerX - 20 + (int)(Math.random() * 40);
            int treeY = centerY - 20 + (int)(Math.random() * 40);
            if (tiles[treeX][treeY].getType() == TileType.FOREST ||
                tiles[treeX][treeY].getType() == TileType.PLAINS) {
                tiles[treeX][treeY].setResource("tree", 3);
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
                        // Вершина горы
                        tiles[x][y] = new Tile(TileType.MOUNTAIN, 50);
                        tiles[x][y].setResource("iron", 5);
                    } else if (distance < 60) {
                        // Склон горы (камни, руды)
                        tiles[x][y] = new Tile(TileType.STONY, 30);
                        if (Math.random() < 0.3) {
                            tiles[x][y].setResource("iron", 2);
                        }
                    } else if (distance < 100) {
                        // Лес
                        tiles[x][y] = new Tile(TileType.FOREST, 20);
                        tiles[x][y].setResource("tree", 5);
                    } else if (distance < 150) {
                        // Равнина
                        tiles[x][y] = new Tile(TileType.PLAINS, 10);
                        if (Math.random() < 0.2) {
                            tiles[x][y].setResource("berry", 3);
                        }
                    } else {
                        // Пляж
                        tiles[x][y] = new Tile(TileType.BEACH, 5);
                    }
                }
            }
        }
        
        // Добавляем реку
        for (int y = centerY - 50; y <= centerY + 50; y++) {
            int riverX = centerX - 20;
            if (riverX >= 0 && riverX < MAP_SIZE && y >= 0 && y < MAP_SIZE) {
                tiles[riverX][y] = new Tile(TileType.RIVER, 0);
            }
        }
    }
    
    public void setPlayerPosition(int x, int y) {
        this.playerX = x;
        this.playerY = y;
    }
    
    public void movePlayer(String direction) {
        int newX = playerX;
        int newY = playerY;
        
        switch (direction.toLowerCase()) {
            case "w": newY--; break;
            case "s": newY++; break;
            case "a": newX--; break;
            case "d": newX++; break;
            default: return;
        }
        
        if (newX < 0 || newX >= MAP_SIZE || newY < 0 || newY >= MAP_SIZE) {
            Colors.println(Colors.RED, "❌ Край света! Дальше бескрайний океан...");
            return;
        }
        
        Tile newTile = tiles[newX][newY];
        if (newTile.getType() == TileType.OCEAN) {
            Colors.println(Colors.RED, "❌ Там океан! Ты не умеешь плавать... Нужна лодка!");
            return;
        }
        
        playerX = newX;
        playerY = newY;
        
        Colors.println(Colors.GREEN, "🚶 Ты переместился");
        showCurrentLocation();
    }
    
    public void showCurrentLocation() {
        Tile current = tiles[playerX][playerY];
        Colors.println(current.getType().getColor(), 
            "\n📍 " + current.getType().getName());
        
        // Показываем расстояние до большого острова
        int distToBig = (int)Math.sqrt(
            Math.pow(playerX - bigIslandX, 2) + 
            Math.pow(playerY - bigIslandY, 2)
        );
        
        if (distToBig < 50) {
            Colors.println(Colors.YELLOW, "Ты на большом острове! Гора совсем рядом.");
        } else if (distToBig < 100) {
            Colors.println(Colors.YELLOW, "Большой остров с горой виднеется на горизонте.");
        } else if (playerX < 600 && playerY < 600) {
            Colors.println(Colors.CYAN, "Ты на маленьком острове. Вдалеке виден большой остров.");
        }
    }
    
    public void exploreArea(Player player) {
        Tile current = tiles[playerX][playerY];
        Colors.println(Colors.CYAN, "\n🔍 Ты исследуешь окрестности...");
        
        if (current.hasResource()) {
            String resource = current.collectResource();
            player.getInventory().addItem(resource, 1);
            Colors.println(Colors.GREEN, "  Ты нашёл " + getResourceName(resource) + "!");
        }
        
        // Случайная находка
        if (Math.random() < 0.2) {
            String[] finds = {"berry", "coconut", "wood_piece", "stone_piece"};
            String find = finds[(int)(Math.random() * finds.length)];
            player.getInventory().addItem(find, 1);
            Colors.println(Colors.GREEN, "  Находка: " + getResourceName(find));
        }
    }
    
    private String getResourceName(String id) {
        switch (id) {
            case "berry": return "🍓 Ягоды";
            case "coconut": return "🥥 Кокос";
            case "wood_piece": return "🪵 Дерево";
            case "stone_piece": return "🪨 Камень";
            default: return id;
        }
    }
    
    public boolean hasTreesAtPlayer() {
        Tile tile = tiles[playerX][playerY];
        return tile.getType() == TileType.FOREST || 
               tile.getType() == TileType.PLAINS;
    }
    
    public boolean hasStonesAtPlayer() {
        Tile tile = tiles[playerX][playerY];
        return tile.getType() == TileType.STONY || 
               tile.getType() == TileType.MOUNTAIN;
    }
    
    public boolean hasAnimalsAtPlayer() {
        Tile tile = tiles[playerX][playerY];
        return tile.getType() == TileType.FOREST || 
               tile.getType() == TileType.PLAINS;
    }
    
    public boolean hasWaterAtPlayer() {
        Tile tile = tiles[playerX][playerY];
        return tile.getType() == TileType.RIVER || 
               tile.getType() == TileType.BEACH;
    }
    
    public boolean chopTreeAtPlayer() {
        if (!hasTreesAtPlayer()) return false;
        
        Tile tile = tiles[playerX][playerY];
        if (tile.getResource().equals("tree")) {
            return tile.useResource();
        }
        return true; // В лесу всегда есть деревья
    }
    
    public boolean mineStoneAtPlayer() {
        if (!hasStonesAtPlayer()) return false;
        
        Tile tile = tiles[playerX][playerY];
        if (tile.getResource().equals("iron")) {
            return tile.useResource();
        }
        return true;
    }
    
    public boolean huntAtPlayer() {
        if (!hasAnimalsAtPlayer()) return false;
        return Math.random() < 0.5; // 50% шанс найти животное
    }
    
    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
    public Tile getTile(int x, int y) { return tiles[x][y]; }
    public int getSmallIslandX() { return smallIslandX; }
    public int getSmallIslandY() { return smallIslandY; }
    public int getBigIslandX() { return bigIslandX; }
    public int getBigIslandY() { return bigIslandY; }
              }
