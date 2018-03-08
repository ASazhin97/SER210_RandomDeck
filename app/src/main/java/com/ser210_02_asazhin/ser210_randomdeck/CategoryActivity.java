package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    Button[] buttonsArray;
    EditText _deckName;
    ArrayList<String> colors;
    Spinner setSpinner;
    String set;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        colors = new ArrayList<String>();

        //Deck names
        _deckName = (EditText) findViewById(R.id.deckNameEdit);
        //buttons
        buttonsArray = new Button[5];
        buttonsArray[0] = (Button) findViewById(R.id.blackButton);
        buttonsArray[1] = (Button) findViewById(R.id.whiteButton);
        buttonsArray[2] = (Button) findViewById(R.id.greenButton);
        buttonsArray[3] = (Button) findViewById(R.id.blueButton);
        buttonsArray[4] = (Button) findViewById(R.id.redButton);

        //spinner
        String[] setsArray = {"Legacy", "Commander", "Modern"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, setsArray);

        setSpinner = (Spinner) findViewById(R.id.setSpinner);
        setSpinner.setAdapter(adapter);
        setSpinner.setEnabled(true);
        setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String set = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
//    public void onItemClick(View view){
////        TextView textView = (TextView) setSpinner.getSelectedView();
////        String result = textView.getText().toString();
////        set = result;
//    }


    /*
    This method changes the int array that manages the color categories
    This array is later sent to the next activity to be used to
    generate the deck list
     */
    //0-4.
    //0 - black, white green blue red.
    public void colorButtonsOnClick(View view){
        for(int i = 0; i < buttonsArray.length; i++){
            if(view == buttonsArray[i]){
                switch(i){
                    case(0)://black
                        if(colors.contains("black")){
                            colors.remove("black");
                        }else{
                            colors.add("black");
                        }
                        break;
                    case(1)://white
                        if(colors.contains("white")){
                            colors.remove("white");
                        }else{
                            colors.add("white");
                        }
                        break;
                    case(2)://green
                        if(colors.contains("green")){
                            colors.remove("green");
                        }else{
                            colors.add("green");
                        }
                        break;
                    case(3)://blue
                        if(colors.contains("blue")){
                            colors.remove("blue");
                        }else{
                            colors.add("blue");
                        }
                        break;
                    case(4)://red
                        if(colors.contains("red")){
                            colors.remove("red");
                        }else{
                            colors.add("red");
                        }
                        break;
                }
            }
        }
    }


    /*
    The user can choose only one item on the spinner
    */


    public void onGenerateClick(View view){
        Intent startDeckListActIntent = new Intent(this, DeckListActivity.class);
        startDeckListActIntent.putExtra("deckName", _deckName.getText().toString());
        startDeckListActIntent.putExtra("colorsArray", colors);
        startDeckListActIntent.putExtra("set", set);

        startActivity(startDeckListActIntent);
    }
}
