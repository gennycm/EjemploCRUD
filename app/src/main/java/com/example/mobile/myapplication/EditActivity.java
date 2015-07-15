package com.example.mobile.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import database.DatabaseHelper;
import model.Restaurant;


public class EditActivity extends ActionBarActivity {
    private String rcvId;
    private DatabaseHelper db;
    private EditText name, address, description, pnumber;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        rcvId = intent.getStringExtra("rcvId");
        setContentView(R.layout.edit_activity);
        setupElements();
        showRestInfo();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        updateButton = (Button) findViewById(R.id.updateBtn);
    }

    private void showRestInfo(){
        Restaurant restaurant = db.getRestaurant(Integer.parseInt(rcvId));
        name.setText(restaurant.getmName());
        address.setText(restaurant.getmAddress());
        description.setText(restaurant.getmDescription());
        pnumber.setText(restaurant.getmPhoneNumber());
    }

    public void updateBtnOnClick(View view){
        Restaurant restaurant = new Restaurant();
        restaurant.setmId(Integer.parseInt(rcvId));
        restaurant.setmName(name.getText().toString());
        restaurant.setmAddress(address.getText().toString());
        restaurant.setmDescription(description.getText().toString());
        restaurant.setmPhoneNumber(pnumber.getText().toString());
        db.updateEntry(restaurant);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
