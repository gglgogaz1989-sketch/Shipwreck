package com.mysteryisland.items;

public class Item {
    private String id;
    private String name;
    private String emoji;
    private ItemCategory category;
    private int count;
    
    public Item(String id, String name, String emoji, ItemCategory category) {
        this(id, name, emoji, category, 1);
    }
    
    public Item(String id, String name, String emoji, ItemCategory category, int count) {
        this.id = id;
        this.name = name;
        this.emoji = emoji;
        this.category = category;
        this.count = count;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public ItemCategory getCategory() { return category; }
    public int getCount() { return count; }
    
    public void setCount(int count) { this.count = count; }
    public void addCount(int amount) { this.count += amount; }
    
    public boolean removeCount(int amount) {
        if (count >= amount) {
            count -= amount;
            return true;
        }
        return false;
    }
    
    public String getDisplayString() {
        return emoji + " " + name + (count > 1 ? " x" + count : "");
    }
}
