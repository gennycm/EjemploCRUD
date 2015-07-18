package com.antonioleiva.materialeverywhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.DatabaseHelper;
import model.Restaurant;


public class NewActivity extends ActionBarActivity {

    private DatabaseHelper db;
    private EditText name, address, description, pnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);
        setupElements();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void setupElements(){
        db = new DatabaseHelper(getApplicationContext());
        name = (EditText) findViewById(R.id.nameTxt);
        address = (EditText) findViewById(R.id.addressTxt);
        description = (EditText)findViewById(R.id.descripTxt);
        pnumber = (EditText)findViewById(R.id.pnumberTxt);
    }

    public void addBtnOnClick(View view){
        Restaurant restaurant = new Restaurant();
        restaurant.setmName(name.getText().toString());
        restaurant.setmAddress(address.getText().toString());
        restaurant.setmDescription(description.getText().toString());
        restaurant.setmPhoneNumber(pnumber.getText().toString());
        db.addRestaurant(restaurant);
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
    }

}
