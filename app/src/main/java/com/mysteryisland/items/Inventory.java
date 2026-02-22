package com.mysteryisland.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<String, Item> items;
    private int maxSlots;
    
    public Inventory() {
        items = new HashMap<>();
        maxSlots = 30;
    }
    
    public void addItem(String id, String name, String emoji, ItemCategory category, int count) {
        if (items.containsKey(id)) {
            items.get(id).addCount(count);
        } else {
            if (items.size() < maxSlots) {
                items.put(id, new Item(id, name, emoji, category, count));
            }
        }
    }
    
    public void addItem(String id, int count) {
        if (items.containsKey(id)) {
            items.get(id).addCount(count);
        }
    }
    
    public boolean hasItem(String id, int count) {
        return items.containsKey(id) && items.get(id).getCount() >= count;
    }
    
    public boolean removeItem(String id, int count) {
        if (!hasItem(id, count)) return false;
        
        Item item = items.get(id);
        if (item.getCount() == count) {
            items.remove(id);
        } else {
            item.removeCount(count);
        }
        return true;
    }
    
    public String getString() {
        if (items.isEmpty()) return "📦 Инвентарь пуст";
        
        StringBuilder sb = new StringBuilder("📦 ИНВЕНТАРЬ:\n");
        
        // Группируем по категориям
        Map<ItemCategory, List<Item>> byCategory = new HashMap<>();
        for (Item item : items.values()) {
            if (!byCategory.containsKey(item.getCategory())) {
                byCategory.put(item.getCategory(), new ArrayList<>());
            }
            byCategory.get(item.getCategory()).add(item);
        }
        
        // Выводим по категориям
        for (ItemCategory category : ItemCategory.values()) {
            if (byCategory.containsKey(category)) {
                sb.append("\n").append(category.getName()).append(":\n");
                for (Item item : byCategory.get(category)) {
                    sb.append("  ").append(item.getEmoji()).append(" ")
                      .append(item.getName()).append(" x").append(item.getCount()).append("\n");
                }
            }
        }
        
        return sb.toString();
    }
    
    public int getItemCount() {
        return items.values().stream().mapToInt(Item::getCount).sum();
    }
}
