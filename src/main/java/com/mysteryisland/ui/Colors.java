package com.mysteryisland.ui;

public class Colors {
    public static final String RESET = "\033[0m";
    public static final String BLACK = "\033[30m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String PURPLE = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";
    public static final String ORANGE = "\033[38;5;214m";
    
    public static void print(String color, String text) {
        System.out.print(color + text + RESET);
    }
    
    public static void println(String color, String text) {
        System.out.println(color + text + RESET);
    }
    
    public static void printHeader(String title) {
        System.out.println();
        println(ORANGE, "╔══════════════════════════════════════╗");
        println(ORANGE, "║     " + centerText(title, 36) + "║");
        println(ORANGE, "╚══════════════════════════════════════╝");
        System.out.println();
    }
    
    private static String centerText(String text, int width) {
        if (text.length() >= width) return text;
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
}
