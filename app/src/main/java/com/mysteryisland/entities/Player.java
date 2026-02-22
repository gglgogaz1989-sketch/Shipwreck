package com.mysteryisland.entities;

import com.mysteryisland.items.Inventory;
import com.mysteryisland.items.ItemCategory;

public class Player {
    private int health;
    private int maxHealth;
    private int hunger;
    private int thirst;
    private int day;
    private Inventory inventory;
    private int x, y;
    
    public Player() {
        this.health = 100;
        this.maxHealth = 100;
        this.hunger = 80;
        this.thirst = 70;
        this.day = 1;
        this.inventory = new Inventory();
        this.x = 500;
        this.y = 500;
        
        // Стартовые предметы
        inventory.addItem("wood_piece", "Древесина", "🪵", ItemCategory.RESOURCE, 5);
        inventory.addItem("stone_piece", "Камень", "🪨", ItemCategory.RESOURCE, 3);
        inventory.addItem("berry", "Ягоды", "🫐", ItemCategory.FOOD, 3);
        inventory.addItem("coconut", "Кокос", "🥥", ItemCategory.FOOD, 1);
        inventory.addItem("raw_meat", "Сырое мясо", "🥩", ItemCategory.FOOD, 1);
        inventory.addItem("wooden_axe", "Деревянный топор", "🪓", ItemCategory.TOOL, 1);
        inventory.addItem("stone_pickaxe", "Каменная кирка", "⛏️", ItemCategory.TOOL, 1);
    }
    
    public void eat(int foodValue) {
        hunger = Math.min(100, hunger + foodValue * 10);
    }
    
    public void rest() {
        health = Math.min(maxHealth, health + 20);
        day++;
        hunger = Math.max(0, hunger - 5);
        thirst = Math.max(0, thirst - 5);
    }
    
    public void decreaseHunger(int amount) {
        hunger = Math.max(0, hunger - amount);
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getHunger() { return hunger; }
    public int getThirst() { return thirst; }
    public Inventory getInventory() { return inventory; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
