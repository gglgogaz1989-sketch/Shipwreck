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
    
    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
