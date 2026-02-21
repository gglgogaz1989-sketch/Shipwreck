package com.mysteryisland.utils;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    
    public static char getKey() {
        try {
            String input = scanner.nextLine();
            return input.length() > 0 ? input.charAt(0) : ' ';
        } catch (Exception e) {
            return ' ';
        }
    }
    
    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.print("❌ Введите число: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
    
    public static int getInt(String prompt, int min, int max) {
        int value;
        do {
            value = getInt(prompt);
            if (value < min || value > max) {
                System.out.println("❌ Введите от " + min + " до " + max);
            }
        } while (value < min || value > max);
        return value;
    }
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
