package com.mysteryisland;

import com.mysteryisland.altar.Altar;
import com.mysteryisland.altar.Campfire;
import com.mysteryisland.entities.Player;
import com.mysteryisland.items.Crafting;
import com.mysteryisland.items.Item;
import com.mysteryisland.items.ItemType;
import com.mysteryisland.save.SaveSystem;
import com.mysteryisland.story.Chapter1;
import com.mysteryisland.ui.Colors;
import com.mysteryisland.ui.Joystick;
import com.mysteryisland.ui.MapDisplay;
import com.mysteryisland.utils.Input;
import com.mysteryisland.world.GameMap;

public class Game {
    private static Game instance;
    private Player player;
    private GameMap gameMap;
    private Campfire campfire;
    private Altar altar;
    private boolean running;
    private int chapter;
    private boolean hasMap;
    
    public Game() {
        instance = this;
        running = true;
        chapter = 1;
    }
    
    public static Game getInstance() {
        return instance;
    }
    
    public void start() {
        showMainMenu();
    }
    
    private void showMainMenu() {
        while (running) {
            Colors.println(Colors.YELLOW, "\n1. Новая игра");
            Colors.println(Colors.YELLOW, "2. Загрузить игру");
            Colors.println(Colors.YELLOW, "3. Выйти");
            
            int choice = Input.getInt("Выбери действие: ", 1, 3);
            
            switch (choice) {
                case 1:
                    newGame();
                    break;
                case 2:
                    loadGame();
                    break;
                case 3:
                    exit();
                    break;
            }
        }
    }
    
    private void newGame() {
        UI.printHeader("ГЛАВА 1: КРУШЕНИЕ");
        Chapter1.play();
        
        player = new Player();
        gameMap = new GameMap();
        campfire = new Campfire();
        altar = new Altar();
        hasMap = false;
        
        // Старт на маленьком острове (координаты 500, 500 - центр маленького острова)
        player.setX(500);
        player.setY(500);
        gameMap.setPlayerPosition(500, 500);
        
        Colors.println(Colors.CYAN, "\n🌴 Ты очнулся на маленьком песчаном острове...");
        Colors.println(Colors.WHITE, "Вдалеке виден большой остров с горой.");
        
        gameLoop();
    }
    
    private void gameLoop() {
        while (running && player.isAlive()) {
            campfire.update();
            player.update();
            showStatus();
            showActions();
            
            int choice = Input.getInt("Что делаешь? ", 1, 13);
            handleAction(choice);
        }
        
        if (!player.isAlive()) {
            gameOver();
        }
    }
    
    private void showStatus() {
        Colors.println(Colors.GREEN, "\n📊 СТАТУС:");
        System.out.println("❤️ Здоровье: " + player.getHealth() + "/" + player.getMaxHealth());
        System.out.println("🍖 Голод: " + player.getHunger() + "/100");
        System.out.println("💧 Жажда: " + player.getThirst() + "/100");
        System.out.println("📦 Инвентарь: " + player.getInventory().getItemCount() + " предметов");
        System.out.println("🌙 Время: " + (player.isDay() ? "День" : "Ночь") + " " + player.getDay());
        
        if (hasMap) {
            gameMap.showCurrentLocation();
        }
    }
    
    private void showActions() {
        Colors.println(Colors.YELLOW, "\n⚡ ДЕЙСТВИЯ:");
        
        int i = 1;
        
        // Ресурсы в зависимости от биома
        if (gameMap.hasTreesAtPlayer()) {
            Colors.println(Colors.WHITE, i++ + ". Срубить дерево 🌲");
        }
        if (gameMap.hasStonesAtPlayer()) {
            Colors.println(Colors.WHITE, i++ + ". Добыть камень 🪨");
        }
        if (gameMap.hasAnimalsAtPlayer()) {
            Colors.println(Colors.WHITE, i++ + ". Охота 🏹");
        }
        if (gameMap.hasWaterAtPlayer()) {
            Colors.println(Colors.WHITE, i++ + ". Набрать воды 💧");
        }
        
        Colors.println(Colors.WHITE, i++ + ". Крафт 🔨");
        Colors.println(Colors.WHITE, i++ + ". Исследовать остров 🔍");
        Colors.println(Colors.ORANGE, i++ + ". Костер 🔥");
        Colors.println(Colors.PURPLE, i++ + ". Алтарь 🛐");
        Colors.println(Colors.WHITE, i++ + ". Переместиться 🕹️");
        
        if (hasMap) {
            Colors.println(Colors.CYAN, i++ + ". Посмотреть карту 🗺️");
        }
        
        Colors.println(Colors.WHITE, i++ + ". Отдыхать 😴");
        Colors.println(Colors.WHITE, i++ + ". Инвентарь 📦");
        Colors.println(Colors.WHITE, i++ + ". Сохранить 💾");
        Colors.println(Colors.WHITE, i++ + ". Выйти в меню 🚪");
    }
    
    private void handleAction(int choice) {
        int baseIndex = 1;
        
        // Динамические действия (зависят от биома)
        if (gameMap.hasTreesAtPlayer() && choice == baseIndex++) {
            chopTree();
            return;
        }
        if (gameMap.hasStonesAtPlayer() && choice == baseIndex++) {
            mineStone();
            return;
        }
        if (gameMap.hasAnimalsAtPlayer() && choice == baseIndex++) {
            hunt();
            return;
        }
        if (gameMap.hasWaterAtPlayer() && choice == baseIndex++) {
            collectWater();
            return;
        }
        
        // Статические действия
        int remainingChoice = choice - baseIndex + 1;
        
        switch (remainingChoice) {
            case 1:
                showCrafting();
                break;
            case 2:
                explore();
                break;
            case 3:
                campfireMenu();
                break;
            case 4:
                altarMenu();
                break;
            case 5:
                move();
                break;
            case 6:
                if (hasMap) {
                    showMap();
                } else {
                    rest();
                }
                break;
            case 7:
                if (hasMap) {
                    rest();
                } else {
                    player.getInventory().show();
                }
                break;
            case 8:
                if (hasMap) {
                    player.getInventory().show();
                } else {
                    saveGame();
                }
                break;
            case 9:
                if (hasMap) {
                    saveGame();
                } else {
                    running = false;
                }
                break;
            case 10:
                running = false;
                break;
            default:
                rest();
        }
    }
    
    private void chopTree() {
        if (gameMap.chopTreeAtPlayer()) {
            player.getInventory().addItem("wood_piece", 1);
            if (Math.random() < 0.3) {
                player.getInventory().addItem("wood_bark", 1);
            }
            if (Math.random() < 0.2) {
                player.getInventory().addItem("wood_leaf", 1);
            }
            if (Math.random() < 0.1) {
                player.getInventory().addItem("big_grass", 1);
            }
            Colors.println(Colors.GREEN, "🌲 Ты срубил дерево!");
        } else {
            Colors.println(Colors.RED, "❌ Здесь нет деревьев!");
        }
        player.decreaseHunger(5);
    }
    
    private void mineStone() {
        if (gameMap.mineStoneAtPlayer()) {
            int amount = 1 + (int)(Math.random() * 2);
            player.getInventory().addItem("stone_piece", amount);
            if (Math.random() < 0.1) {
                player.getInventory().addItem("pebble", 1);
            }
            if (Math.random() < 0.05) {
                player.getInventory().addItem("raw_iron", 1);
            }
            Colors.println(Colors.GREEN, "🪨 Ты добыл камень! +" + amount);
        } else {
            Colors.println(Colors.RED, "❌ Здесь нет камней!");
        }
        player.decreaseHunger(5);
    }
    
    private void hunt() {
        if (gameMap.huntAtPlayer()) {
            int food = 1 + (int)(Math.random() * 2);
            player.getInventory().addItem("raw_meat", food);
            if (Math.random() < 0.2) {
                player.getInventory().addItem("raw_egg", 1);
            }
            if (Math.random() < 0.1) {
                player.getInventory().addItem("feather", 1);
            }
            Colors.println(Colors.GREEN, "🏹 Охота успешна! +" + food + " мяса");
        } else {
            Colors.println(Colors.RED, "❌ Никого не нашлось...");
        }
        player.decreaseHunger(10);
    }
    
    private void collectWater() {
        player.getInventory().addItem("water", 1);
        Colors.println(Colors.CYAN, "💧 Ты набрал воды!");
        player.decreaseHunger(2);
    }
    
    private void showCrafting() {
        Crafting.showMenu(player, gameMap);
        
        // Проверяем, скрафтил ли игрок карту
        if (!hasMap && player.getInventory().hasItem("map")) {
            hasMap = true;
            Colors.println(Colors.GREEN, "\n🗺️ Теперь у тебя есть карта! Используй её чтобы видеть своё местоположение.");
        }
    }
    
    private void explore() {
        gameMap.exploreArea(player);
    }
    
    private void showMap() {
        MapDisplay.showMap(gameMap, player);
    }
    
    private void campfireMenu() {
        while (true) {
            campfire.showStatus();
            
            Colors.println(Colors.YELLOW, "\n🔥 КОСТЁР:");
            Colors.println(Colors.WHITE, "1. Зажечь костёр");
            Colors.println(Colors.WHITE, "2. Положить еду");
            Colors.println(Colors.WHITE, "3. Забрать готовое");
            Colors.println(Colors.WHITE, "4. Добавить дров");
            Colors.println(Colors.WHITE, "5. Назад");
            
            int choice = Input.getInt("Выбери: ", 1, 5);
            
            switch (choice) {
                case 1:
                    campfire.light(player.getInventory());
                    break;
                case 2:
                    player.getInventory().show();
                    String item = Input.getString("Что положишь? ");
                    campfire.addToCampfire(player.getInventory(), item);
                    break;
                case 3:
                    Item cooked = campfire.collectCooked(player.getInventory());
                    if (cooked != null) {
                        player.getInventory().addItem(cooked.getType().name().toLowerCase(), 1);
                    }
                    break;
                case 4:
                    campfire.addFuel(player.getInventory());
                    break;
                case 5:
                    return;
            }
        }
    }
    
    private void altarMenu() {
        while (true) {
            altar.showStatus();
            
            Colors.println(Colors.PURPLE, "\n🛐 АЛТАРЬ:");
            Colors.println(Colors.WHITE, "1. Принести жертву");
            Colors.println(Colors.WHITE, "2. Назад");
            
            int choice = Input.getInt("Выбери: ", 1, 2);
            
            if (choice == 1) {
                player.getInventory().show();
                String item = Input.getString("Что принесёшь в жертву? ");
                altar.offer(player.getInventory(), item);
            } else {
                return;
            }
        }
    }
    
    private void move() {
        Joystick.move(player, gameMap);
        gameMap.setPlayerPosition(player.getX(), player.getY());
    }
    
    private void rest() {
        player.rest();
    }
    
    private void saveGame() {
        SaveSystem.save(player, gameMap, chapter, hasMap);
    }
    
    private void loadGame() {
        SaveSystem.load();
    }
    
    private void gameOver() {
        UI.printHeader("ИГРА ОКОНЧЕНА");
        Colors.println(Colors.RED, "Ты не выжил на острове...");
        
        if (Input.getYesNo("Начать заново?")) {
            newGame();
        } else {
            showMainMenu();
        }
    }
    
    private void exit() {
        if (Input.getYesNo("Точно выйти?")) {
            Colors.println(Colors.PURPLE, "До встречи!");
            System.exit(0);
        }
    }
    }
