package com.natiki.seaboard;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main extends Activity implements View.OnClickListener{

    //View
    TextView stationName;
    TextView stationID;
    Button btnSettings;

    //Preferences
    final String STATION_ID = "id";
    final String STATION_NAME = "name";
    SharedPreferences sPref;

    String stID ="ID";
    String stName ="NAME";

   // Station station = new Station();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stationName = (TextView) findViewById(R.id.stationName);
        stationID = (TextView) findViewById(R.id.stationID);
        btnSettings = (Button) findViewById(R.id.btnSettings);

        btnSettings.setOnClickListener(this);

        loadText();

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

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        if (stName != null) {
        ed.putString(STATION_ID, stID);
        ed.putString(STATION_NAME, stName);
        ed.commit();
        Toast.makeText(this, "Station saved", Toast.LENGTH_SHORT).show();}
        else stName = "Station Name";
    }


    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedId = sPref.getString(STATION_ID, "");
        String savedName = sPref.getString(STATION_NAME, "");
        stationName.setText(savedId);
        stationID.setText(savedName);
        Toast.makeText(this, "Station Loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        stID = data.getStringExtra("name");

        stName = data.getStringExtra("id");

        stationName.setText(stName);
        stationID.setText(stID);
        saveText();

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectStation.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onDestroy() {
        saveText();
        super.onDestroy();
    }
}