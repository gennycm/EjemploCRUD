package com.example.mobile.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import model.Restaurant;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    /*Variables*/
        EditText name, address, id;
        Button addButton, deleteButton;
        TextView textView;
        List<Restaurant> list = new ArrayList<Restaurant>();
        DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Setup elements
        setupElements();
        list = db.getAllRestaurantList();
        if(list.size() == 0){
            fillDb();
        }
        print(list);
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
        id = (EditText) findViewById(R.id.idTxt);
        addButton = (Button) findViewById(R.id.addBtn);
        deleteButton = (Button) findViewById(R.id.deleteBtn);
        textView = (TextView) findViewById(R.id.restaurantTv);
        addButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
    }

    protected void fillDb(){
        Restaurant restaurant = new Restaurant();
        restaurant.name = "BBT";
        restaurant.address = "Prol. Montejo";
        db.addRestaurant(restaurant);
        restaurant.name = "T.G.I Fridays";
        restaurant.address = "Prol Montejo";
        db.addRestaurant(restaurant);
        list = db.getAllRestaurantList();
    }

    private void print(List<Restaurant> list) {
        String value = "";
        for(Restaurant rest : list){
            value = value+rest.id+"- Name: "+rest.name+" || Address: "+rest.address+"\n";
        }
        textView.setText(value);
    }



    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if(v == findViewById(R.id.deleteBtn)){
            textView.setText("");
            String restaurant_id = id.getText().toString();
            db.deleteEntry(Integer.parseInt(restaurant_id));
            list = db.getAllRestaurantList();
            print(list);
        }
        if(v == findViewById(R.id.addBtn)){
            textView.setText("");
            Restaurant restaurant = new Restaurant();
            restaurant.name = name.getText().toString();
            restaurant.address = address.getText().toString();
            db.addRestaurant(restaurant);
            list = db.getAllRestaurantList();
            print(list);
        }
    }
}
