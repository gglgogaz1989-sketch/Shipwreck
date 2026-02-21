package com.mysteryisland.entities;

import com.mysteryisland.items.Inventory;
import com.mysteryisland.ui.Colors;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int hunger;
    private int thirst;
    private int day;
    private boolean isDay;
    private Inventory inventory;
    private int x, y;
    
    public Player() {
        this.name = "Выживший";
        this.health = 100;
        this.maxHealth = 100;
        this.hunger = 80;
        this.thirst = 70;
        this.day = 1;
        this.isDay = true;
        this.inventory = new Inventory();
        this.x = 500;
        this.y = 500;
        
        // Стартовые предметы на маленьком острове
        inventory.addItem("wood_piece", 2);
        inventory.addItem("stone_piece", 1);
        inventory.addItem("berry", 3);
        inventory.addItem("coconut", 1);
        inventory.addItem("big_grass", 2);
    }
    
    public void update() {
        if (hunger > 0) hunger -= 1;
        if (thirst > 0) thirst -= 1;
        
        if (hunger <= 0 || thirst <= 0) {
            health -= 2;
        }
        
        if (health > maxHealth) health = maxHealth;
        if (health < 0) health = 0;
    }
    
    public void eat(int foodAmount) {
        hunger += foodAmount * 10;
        if (hunger > 100) hunger = 100;
        Colors.println(Colors.GREEN, "🍖 Сытость восстановлена!");
    }
    
    public void drink(int waterAmount) {
        thirst += waterAmount * 10;
        if (thirst > 100) thirst = 100;
        Colors.println(Colors.CYAN, "💧 Жажда утолена!");
    }
    
    public void rest() {
        health += 20;
        if (health > maxHealth) health = maxHealth;
        Colors.println(Colors.YELLOW, "😴 Ты отдохнул. +20 здоровья");
        
        if (isDay) {
            isDay = false;
            Colors.println(Colors.BLUE, "🌙 Наступила ночь");
        } else {
            isDay = true;
            day++;
            Colors.println(Colors.YELLOW, "☀️ Наступил день " + day);
        }
    }
    
    public void decreaseHunger(int amount) {
        hunger = Math.max(0, hunger - amount);
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    // Геттеры и сеттеры
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getHunger() { return hunger; }
    public int getThirst() { return thirst; }
    public int getDay() { return day; }
    public boolean isDay() { return isDay; }
    public Inventory getInventory() { return inventory; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
