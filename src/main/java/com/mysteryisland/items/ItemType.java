package com.mysteryisland.items;

public enum ItemType {
    // РАСТИТЕЛЬНОСТЬ
    WOOD_BARK("Кора дерева", "🪵", 1, ItemCategory.RESOURCE),
    WOOD_PIECE("Кусок дерева", "🪓", 1, ItemCategory.RESOURCE),
    WOOD_LEAF("Листик дерева", "🍃", 1, ItemCategory.RESOURCE),
    COCONUT("Кокос", "🥥", 2, ItemCategory.FOOD),
    GRASS("Трава", "🌿", 1, ItemCategory.RESOURCE),
    BIG_GRASS("Большая трава", "🌾", 2, ItemCategory.RESOURCE),
    BERRY("Ягода", "🫐", 1, ItemCategory.FOOD),
    
    // КАМНИ
    STONE("Камень", "🪨", 2, ItemCategory.RESOURCE),
    STONE_PIECE("Кусок камня", "🪨", 1, ItemCategory.RESOURCE),
    PEBBLE("Камушек", "🪸", 1, ItemCategory.RESOURCE),
    
    // РУДЫ
    RAW_IRON("Сырое железо", "⛓️", 3, ItemCategory.ORE),
    IRON("Железо", "⚙️", 4, ItemCategory.INGOT),
    RAW_GOLD("Сырое золото", "🪙", 5, ItemCategory.ORE),
    GOLD("Золото", "🏅", 6, ItemCategory.INGOT),
    RAW_COPPER("Сырая медь", "🔴", 2, ItemCategory.ORE),
    COPPER("Медь", "🟠", 3, ItemCategory.INGOT),
    RAW_DIAMOND("Сырой алмаз", "💎", 10, ItemCategory.GEM),
    DIAMOND("Алмаз", "💎", 20, ItemCategory.GEM),
    RAW_SILVER("Сырое серебро", "⚪", 4, ItemCategory.ORE),
    SILVER("Серебро", "🥈", 5, ItemCategory.INGOT),
    
    // ТЕХНОЛОГИИ
    PLASTIC("Пластик", "🧴", 2, ItemCategory.COMPONENT),
    BOTTLE("Бутылка", "🍾", 1, ItemCategory.COMPONENT),
    GLASS_BOTTLE("Стеклянная бутылка", "🧪", 2, ItemCategory.COMPONENT),
    OIL_BOTTLE("Бутылка с нефтью", "🛢️", 3, ItemCategory.COMPONENT),
    WIRES("Провода", "〰️", 3, ItemCategory.COMPONENT),
    PROCESSOR("Процессор", "💻", 10, ItemCategory.COMPONENT),
    METAL_SCRAP("Металлолом", "🔩", 2, ItemCategory.COMPONENT),
    COPROCESSOR("Сопроцессор", "🖥️", 8, ItemCategory.COMPONENT),
    MECHANISM("Механизм", "⚙️", 5, ItemCategory.COMPONENT),
    PROPELLER("Пропеллер", "🌀", 4, ItemCategory.COMPONENT),
    SMALL_MOTOR("Моторчик", "🔧", 6, ItemCategory.COMPONENT),
    OLD_MOTOR("Старый мотор", "🔨", 7, ItemCategory.COMPONENT),
    
    // ИНСТРУМЕНТЫ
    WOODEN_PICKAXE("Деревянная кирка", "⛏️", 5, ItemCategory.TOOL),
    WOODEN_AXE("Деревянный топор", "🪓", 5, ItemCategory.TOOL),
    STONE_PICKAXE("Каменная кирка", "⛏️", 10, ItemCategory.TOOL),
    STONE_AXE("Каменный топор", "🪓", 10, ItemCategory.TOOL),
    
    // ЕДА
    RAW_MEAT("Сырое мясо", "🥩", 3, ItemCategory.FOOD),
    RAW_FISH("Сырая рыба", "🐟", 2, ItemCategory.FOOD),
    FISH("Рыба", "🐠", 4, ItemCategory.FOOD),
    
    // ОСОБЫЕ
    WATER("Вода", "💧", 1, ItemCategory.LIQUID),
    OIL("Нефть", "🛢️", 5, ItemCategory.LIQUID),
    POPYE("Попьё", "🧃", 2, ItemCategory.FOOD);  // напиток
    
    private final String displayName;
    private final String icon;
    private final int baseValue;
    private final ItemCategory category;
    
    ItemType(String displayName, String icon, int baseValue, ItemCategory category) {
        this.displayName = displayName;
        this.icon = icon;
        this.baseValue = baseValue;
        this.category = category;
    }
    
    public String getDisplayName() { return displayName; }
    public String getIcon() { return icon; }
    public int getBaseValue() { return baseValue; }
    public ItemCategory getCategory() { return category; }
}
