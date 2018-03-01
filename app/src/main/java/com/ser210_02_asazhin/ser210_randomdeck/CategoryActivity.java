package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CategoryActivity extends AppCompatActivity {
    Button[] buttonsArray;
    EditText _deckName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Deck names
        _deckName = (EditText) findViewById(R.id.deckNameEdit);
        //buttons

        //spinner

    }

//    public void colorButtonsOnClick(View view){
//        /*
//        creates an array of 0 and 1 to specfy each color
//        0 = black
//        1 = white
//        2 = green
//        3 = blue
//        5 = red
//        */
//    }
//
//    public void spinnerOnItemChoose(View view){
//        /*
//        The user can choose only one item on the spinner
//         */
//    }

    public void onGenerateClick(View view){
        Intent startDeckListActIntent = new Intent(this, DeckListActivity.class);
        startDeckListActIntent.putExtra("deckName", _deckName.getText().toString());
        startActivity(startDeckListActIntent);
    }
}
