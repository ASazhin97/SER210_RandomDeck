package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onNewDeckClick(View view){
        Intent startCatActIntent = new Intent(this, CategoryActivity.class);
        startActivity(startCatActIntent);
    }
}
