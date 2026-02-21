package com.mysteryisland.ui;

public class UI {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void printTitle() {
        Colors.println(Colors.CYAN, "  ╔═══════════════════════════════╗");
        Colors.println(Colors.CYAN, "  ║                               ║");
        Colors.println(Colors.ORANGE, "  ║   ТАИНСТВЕННЫЙ ОСТРОВ v1.0   ║");
        Colors.println(Colors.CYAN, "  ║                               ║");
        Colors.println(Colors.CYAN, "  ╚═══════════════════════════════╝");
    }
    
    public static void printHeader(String title) {
        Colors.printHeader(title);
    }
}
