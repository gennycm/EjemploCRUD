/*
 * Copyright (C) 2014 Antonio Leiva Gordillo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antonioleiva.materialeverywhere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import model.Restaurant;


public class HomeActivity extends BaseActivity {

    private DrawerLayout drawer;
    private static DatabaseHelper db;
    private static GridView gridView;
    protected static List<Restaurant> list = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.drawable.ic_ab_drawer);
        db = new DatabaseHelper(getApplicationContext());
        fillDb();
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new GridViewAdapter());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
                intent.putExtra("r_id", String.valueOf(HomeActivity.list.get(i).getmId()));
                intent.putExtra("r_name", HomeActivity.list.get(i).getmName());
                intent.putExtra("r_description", HomeActivity.list.get(i).getmDescription());
                intent.putExtra("r_phone", HomeActivity.list.get(i).getmPhoneNumber());
                intent.putExtra("r_address", HomeActivity.list.get(i).getmAddress());
                startActivity(intent);
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
    }

    public static void deleteEntry(long id){
        db.deleteEntry(id);
        fillDb();
        gridView.setAdapter(new GridViewAdapter());
    }

    private static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException
                    (l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }

    private static  void fillDb(){
        if(db.getAllRestaurantList().size()==0) {
            Restaurant restaurant = new Restaurant();
            restaurant.setmName("BBT");
            restaurant.setmDescription("¡Las más grandes hamburguesas de la ciudad las encuentras en BBT!");
            restaurant.setmAddress("Prol. Montejo");
            restaurant.setmPhoneNumber("1223344");
            db.addRestaurant(restaurant);
            restaurant.setmName("T.G.I Fridays");
            restaurant.setmDescription("¡Para festejar el inicio del fin de semana, no hay mejor lugar que Fridays!");
            restaurant.setmAddress("Prol. Montejo");
            restaurant.setmPhoneNumber("2334455");
            db.addRestaurant(restaurant);
            restaurant.setmName("Los trompos");
            restaurant.setmDescription("¡Los mejores nachos, variedad de carnes y el mejor servicio solo en Los Trompos!");
            restaurant.setmAddress("Pza. Dorada");
            restaurant.setmPhoneNumber("9884444");
            db.addRestaurant(restaurant);
            restaurant.setmName("Boston's");
            restaurant.setmDescription("¡Los precios más caros los encuentras sólo aquí!");
            restaurant.setmAddress("Al lado de Altabrisa");
            restaurant.setmPhoneNumber("1223344");
            db.addRestaurant(restaurant);
            restaurant.setmName("Super Pizza");
            restaurant.setmDescription("¡Para llevar!");
            restaurant.setmAddress("Contraesquina de Plaza Oriente");
            restaurant.setmPhoneNumber("2334455");
            db.addRestaurant(restaurant);
            restaurant.setmName("KFC");
            restaurant.setmDescription("¡Tiene pollo, sabe a pollo... Es de pollo!");
            restaurant.setmAddress("Ya no se me ocurre otra cosa que poner :(");
            restaurant.setmPhoneNumber("9884444");
            db.addRestaurant(restaurant);
        }
        list = db.getAllRestaurantList();
    }

    @Override protected int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(MenuItem menuItem){
        Toast t = Toast.makeText(this,"cliqueado menuitem en home",Toast.LENGTH_SHORT);
        t.show();
    }

    private static class GridViewAdapter extends BaseAdapter {

        @Override public int getCount() {
            return HomeActivity.list.size();
        }

        @Override public Object getItem(int i) {
            return "Item " + String.valueOf(i + 1);
        }

        @Override public long getItemId(int i) {
            return i;
        }

        @Override public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.grid_item, viewGroup, false);
            }

            TextView restaurant_name = (TextView) view.findViewById(R.id.restaurant_name);
            TextView restaurant_phone = (TextView) view.findViewById(R.id.restaurant_phone);

            restaurant_name.setText(HomeActivity.list.get(i).getmName());
            restaurant_phone.setText(HomeActivity.list.get(i).getmPhoneNumber());

            return view;
        }
    }

}
