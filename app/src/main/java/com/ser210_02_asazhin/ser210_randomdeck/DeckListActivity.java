package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DeckListActivity extends AppCompatActivity {
    String _deckName;
    String[] deck;
    public String[] deckArray;

    String set;
    ArrayList<String> colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_list);


        TextView nameText = (TextView) findViewById(R.id.DeckListName);
        set = getIntent().getStringExtra("set");
        _deckName = getIntent().getStringExtra("deckName");
        colors = getIntent().getStringArrayListExtra("colorsArray");
        colors.add(set);
        GenerateDeckAsync d = new GenerateDeckAsync();
        d.execute(colors);


        try {
            deck = d.get();
            ArrayAdapter<String> ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deck);

            ListView deckList = (ListView) findViewById(R.id.deckListView);
            deckList.setAdapter(ListAdapter);

            nameText.setText(_deckName);
        } catch(ExecutionException e){
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }












    }
}
