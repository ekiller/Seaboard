package com.natiki.seaboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity {

    //View
    TextView stationName;
    TextView stationID;

    //Preferences
    final String STATION_ID = "id";
    final String STATION_NAME = "name";
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stationName = (TextView) findViewById(R.id.stationName);
        stationID = (TextView) findViewById(R.id.stationID);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        switch (id){
            case R.id.selectStation:
                Intent intent = new Intent(this, SelectStation.class);
                startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }
}

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(STATION_ID, "");
        ed.commit();
        Toast.makeText(this, "Station saved", Toast.LENGTH_SHORT).show();
    }


    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(STATION_ID, "");
        stationName.setText(savedText);
        Toast.makeText(this, "Station Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        String name = data.getStringExtra("name");

    }