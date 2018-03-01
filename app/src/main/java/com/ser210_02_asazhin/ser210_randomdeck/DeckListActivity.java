package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DeckListActivity extends AppCompatActivity {
    String _deckNmae;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_list);

        TextView nameText = (TextView) findViewById(R.id.DeckListName);
        String _deckName = getIntent().getStringExtra("deckName");
        nameText.setText(_deckName);

    }
}
