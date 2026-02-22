package com.mysteryisland.items;

public enum ItemCategory {
    RESOURCE("📦 Ресурсы"),
    TOOL("🔧 Инструменты"),
    FOOD("🍖 Еда"),
    WEAPON("⚔️ Оружие"),
    MATERIAL("🧱 Материалы");
    
    private final String name;
    
    ItemCategory(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
