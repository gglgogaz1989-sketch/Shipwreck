package com.mysteryisland.items;

public class Item {
    private ItemType type;
    private int count;
    private int durability;  // для инструментов
    
    public Item(ItemType type) {
        this(type, 1);
    }
    
    public Item(ItemType type, int count) {
        this.type = type;
        this.count = count;
        this.durability = (type.getCategory() == ItemCategory.TOOL) ? 100 : 0;
    }
    
    public String getName() {
        return type.getDisplayName();
    }
    
    public String getIcon() {
        return type.getIcon();
    }
    
    public String getDisplayString() {
        return type.getIcon() + " " + type.getDisplayName() + (count > 1 ? " x" + count : "");
    }
    
    public ItemType getType() { return type; }
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
    
    public int getDurability() { return durability; }
    public void setDurability(int durability) { this.durability = durability; }
    public void useTool() { 
        if (type.getCategory() == ItemCategory.TOOL) {
            durability -= 5;
        }
    }
    public boolean isBroken() { return durability <= 0; }
}
