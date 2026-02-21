public void showCurrentLocation(Player player) {
    System.out.println("📍 Ты на координатах X=" + player.getX() + " Y=" + player.getY());
}

public void exploreArea(Player player) {
    System.out.println("🔍 Ты исследовал окрестности...");
    // Добавь логику поиска
}

public boolean isValidPosition(int x, int y) {
    return x >= 0 && x < 1000 && y >= 0 && y < 1000;
}

public String getTerrainIcon(int x, int y) {
    return "⬜"; // временно
}

public String getTerrainColor(int x, int y) {
    return Colors.WHITE; // временно
}
