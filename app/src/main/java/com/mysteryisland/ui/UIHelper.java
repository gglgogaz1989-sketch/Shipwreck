package com.mysteryisland.ui;

import android.content.Context;
import android.widget.Toast;

public class UIHelper {
    
    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    
    public static String getHealthBar(int health, int maxHealth) {
        int hearts = health / 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hearts; i++) {
            sb.append("❤️");
        }
        return sb.toString();
    }
    
    public static String getHungerBar(int hunger) {
        int foodIcons = hunger / 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < foodIcons; i++) {
            sb.append("🍖");
        }
        return sb.toString();
    }
}
