package com.mysteryisland;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView statusText;
    private TextView hungerText;
    private TextView locationText;
    private TextView coordinatesText;
    private TextView treesText;
    private TextView stonesText;
    private TextView berriesText;
    private TextView animalsText;
    private TextView inventoryText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        statusText = findViewById(R.id.statusText);
        hungerText = findViewById(R.id.hungerText);
        locationText = findViewById(R.id.locationText);
        coordinatesText = findViewById(R.id.coordinatesText);
        treesText = findViewById(R.id.treesText);
        stonesText = findViewById(R.id.stonesText);
        berriesText = findViewById(R.id.berriesText);
        animalsText = findViewById(R.id.animalsText);
        inventoryText = findViewById(R.id.inventoryText);
        
        // ИГРОК САМ ВСЁ ДЕЛАЕТ, А ИГРА ПРОСТО ПОКАЗЫВАЕТ
        updateInfo();
    }
    
    private void updateInfo() {
        // Тут только обновление информации
        // Игрок сам решает что делать через другие кнопки/клавиши
    }
}
