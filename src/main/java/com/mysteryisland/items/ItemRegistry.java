package com.mysteryisland.items;

import java.util.HashMap;
import java.util.Map;

public class ItemRegistry {
    private static Map<String, ItemType> itemsById = new HashMap<>();
    
    static {
        // Регистрируем все предметы
        for (ItemType type : ItemType.values()) {
            itemsById.put(type.name().toLowerCase(), type);
        }
    }
    
    public static Item createItem(String id) {
        ItemType type = itemsById.get(id.toLowerCase());
        if (type != null) {
            return new Item(type);
        }
        return null;
    }
    
    public static Item createItem(String id, int count) {
        Item item = createItem(id);
        if (item != null) {
            item.setCount(count);
        }
        return item;
    }
    
    public static ItemType getType(String id) {
        return itemsById.get(id.toLowerCase());
    }
    
    public static boolean exists(String id) {
        return itemsById.containsKey(id.toLowerCase());
    }
    
    public static void listAllItems() {
        System.out.println("\n📋 ВСЕ ПРЕДМЕТЫ:");
        
        for (ItemCategory category : ItemCategory.values()) {
            System.out.println("\n" + category.getDisplayName() + ":");
            for (ItemType type : ItemType.values()) {
                if (type.getCategory() == category) {
                    System.out.println("  " + type.getIcon() + " " + type.getDisplayName() + 
                                     " (ценность: " + type.getBaseValue() + ")");
                }
            }
        }
    }
}
