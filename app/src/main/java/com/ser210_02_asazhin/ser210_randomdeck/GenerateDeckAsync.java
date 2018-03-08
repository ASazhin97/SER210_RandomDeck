package com.ser210_02_asazhin.ser210_randomdeck;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by alexa on 3/4/2018.
 */

public class GenerateDeckAsync extends AsyncTask<ArrayList<String>, Integer, String[]> {
    String[] deckList;
    String set;
    ArrayList<JSONObject> cards;
    ArrayList<String> colors;


    protected String[] doInBackground(ArrayList<String>... categories){
        ArrayList<String> data = categories[0];
        //String deckList;



        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try{
            //create URL to get the data from
            URL url = new URL("https://community-deckbrew.p.mashape.com/mtg/cards?mashape-key=LpjuiLPka7mshcWdWoYQA4dxJpeZp1nLNkwjsnD19V2Y7lz6MO");

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream in = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));

            String JSONSString = getJSonStringFromBuffer(reader);
            Log.i("RAW DATA",JSONSString);
            cards = new ArrayList<JSONObject>();
            try{
                JSONArray jsonArray = new JSONArray(JSONSString);

                int count = jsonArray.length();
                for (int i = 0; i<count; i++){

                    cards.add(jsonArray.getJSONObject(i));
                }

            } catch (JSONException e){
                Log.i("ERRRRR","gOd hElP Us");
                e.printStackTrace();
            }

            //now that we have the list array of cards we need to get the
            //cards that we need out of it


            //0 - Deck type, //1 - color 1
//            buttonsArray[0] = (Button) findViewById(R.id.blackButton);
//            buttonsArray[1] = (Button) findViewById(R.id.whiteButton);
//            buttonsArray[2] = (Button) findViewById(R.id.greenButton);
//            buttonsArray[3] = (Button) findViewById(R.id.blueButton);
//            buttonsArray[4] = (Button) findViewById(R.id.redButton);

            //WHEN I ADDED THIS CODE THE ERRORS SEEM TO START HAPPENING
            int DeckSize = 40;
            if(data.get(data.size()-1).equals("Modern"))
                DeckSize = 60;
            if(data.get(data.size()-1).equals("Commander"))
                DeckSize = 100;
            if(data.get(data.size()-1).equals("Legacy"))
                DeckSize = 40;

            deckList = new String[DeckSize];

            int num = 0;
            for(int i = 0; i<DeckSize; i++){
                int random = (int)Math.random()*cards.size();
                JSONArray c = cards.get(random).getJSONArray("colors");
                boolean good = true;
                for(int s = 0;s<c.length();s++){
                    if(data.contains(c.get(s))){
                        good=false;
                    }
                }
                if(good) {
                    deckList[num] = cards.get(i).getString("name");
                    num++;
                }
              //  cards.remove(cards.get(i));
            }
            for(String s : deckList){
                Log.i("Name",s);
            }

        }catch(Exception e){
            Log.e("err", e.getMessage());
        } finally {
            if(connection !=null) connection.disconnect();
            if(reader != null){
                try{
                    reader.close();
                } catch(IOException io) {
                    Log.e("reader error", "Reader Closing Error");
                    return null;
                }
            }
        }

        // White black blue green red
        // red, white
        //white, red, black


        return deckList;
    }

    public String[] getDeckList(){
        return deckList;
    }

    protected void onProgressUpdate(Integer... progress) {

    }


    protected void onPostExceute(String[] result){
        result = deckList;
        super.onPostExecute(result);



    }

    private String getJSonStringFromBuffer(BufferedReader br) throws Exception{
        StringBuffer buffer = new StringBuffer();
        String line;
        while((line=br.readLine())!=null){
            buffer.append(line + "\n");
        }
        if(buffer.length()==0){
            return null;
        }
        return buffer.toString();
    }




}
