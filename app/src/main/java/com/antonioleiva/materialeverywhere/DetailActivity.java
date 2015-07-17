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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import database.DatabaseHelper;


public class DetailActivity extends BaseActivity {

    private TextView mRId, mRName, mRDescription, mRPhone, mRAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupElements();

    }

    private void setupElements(){
        Intent intent = getIntent();
        // Initialize
        mRId = (TextView)findViewById(R.id.r_id);
        mRName = (TextView)findViewById(R.id.r_name);
        mRDescription = (TextView)findViewById(R.id.r_description);
        mRPhone = (TextView)findViewById(R.id.r_phone);
        mRAddress = (TextView)findViewById(R.id.r_address);
        // Set content
        mRId.setText(intent.getStringExtra("r_id"));
        mRName.setText(intent.getStringExtra("r_name"));
        mRDescription.setText(intent.getStringExtra("r_description"));
        mRPhone.setText(intent.getStringExtra("r_phone"));
        mRAddress.setText(intent.getStringExtra("r_address"));
    }

    @Override protected int getLayoutResource() {
        return R.layout.activity_detail;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void showToast(MenuItem menuItem){
        Toast t = Toast.makeText(this,"cliqueado menuitem en detalles",Toast.LENGTH_SHORT);
        t.show();
    }

    public void delete(View v){
        String name = mRName.getText().toString();
        Long id = Long.valueOf(mRId.getText().toString()).longValue();
        HomeActivity.deleteEntry(id);
        Toast.makeText(this,"Eliminado el restaurante " + name,Toast.LENGTH_SHORT).show();
        finish();
    }

    public void edit(View v){
        Intent i = new Intent(this,EditActivity.class);
        i.putExtra("rcvId",mRId.getText().toString());
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.home) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
