package com.mysteryisland.items;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items;
    
    public Inventory() {
        items = new HashMap<>();
    }
    
    public void addItem(String item, int count) {
        items.put(item, items.getOrDefault(item, 0) + count);
    }
    
    public boolean hasItem(String item, int count) {
        return items.getOrDefault(item, 0) >= count;
    }
    
    public void removeItem(String item, int count) {
        int current = items.getOrDefault(item, 0);
        if (current <= count) {
            items.remove(item);
        } else {
            items.put(item, current - count);
        }
    }
    
    public void show() {
        System.out.println("\n📦 ИНВЕНТАРЬ:");
        if (items.isEmpty()) {
            System.out.println("  Пусто");
            return;
        }
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String icon = getIcon(entry.getKey());
            System.out.println("  " + icon + " " + entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private String getIcon(String item) {
        switch (item) {
            case "wood_piece": return "🪵";
            case "stone_piece": return "🪨";
            case "berry": return "🫐";
            case "raw_meat": return "🥩";
            case "water": return "💧";
            default: return "📦";
        }
    }
}
