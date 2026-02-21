package com.mysteryisland.items;

public enum ItemCategory {
    RESOURCE("Ресурс"),
    ORE("Руда"),
    INGOT("Слиток"),
    GEM("Драгоценность"),
    COMPONENT("Компонент"),
    TOOL("Инструмент"),
    FOOD("Еда"),
    LIQUID("Жидкость");
    
    private final String displayName;
    
    ItemCategory(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() { return displayName; }
}
