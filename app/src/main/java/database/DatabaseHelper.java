package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;

/**
 * Created by Lenova-i7quad on 30/06/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name
    public static String DATABASE_NAME = "restaurant_database";

    // Current version of database
    private static final int DATABASE_VERSION = 1;

    // Name of table
    private static final String TABLE_RESTAURANTS = "restaurants";

    // All Keys used in table
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "phone_number";

    public static String TAG = "tag";

    //Restaurants Table Create Query
    /**
     * CREATE TABLE restaurants ( id INTEGER PRIMARY KEY AUTOINCREMENT, name
     * TEXT,address TEXT);
     */

    private static final String CREATE_TABLE_RESTAURANTS = "CREATE TABLE "
            + TABLE_RESTAURANTS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
            + KEY_ADDRESS + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_RESTAURANTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table if exists if database is upgraded
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RESTAURANTS);
        onCreate(db);
    }

    public long addRestaurant(Restaurant restaurant){
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.name);
        values.put(KEY_ADDRESS, restaurant.address);

        // insert row in restaurants table

        long insert = db.insert(TABLE_RESTAURANTS, null, values);

        return insert;
    }

    public int updateEntry(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, restaurant.name);
        values.put(KEY_ADDRESS, restaurant.address);

        // update row in restaurants table base on restaurants.is value
        return db.update(TABLE_RESTAURANTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(restaurant.id) });
    }

    public void deleteEntry(long id) {
        // delete row in restaurants table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RESTAURANTS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }


    public Restaurant getRestaurant(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // SELECT * FROM restaurant WHERE id = ?;
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANTS + " WHERE "
                + KEY_ID + " = " + id;
        Log.d(TAG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Restaurant restaurant = new Restaurant();
        restaurant.id = c.getInt(c.getColumnIndex(KEY_ID));
        restaurant.address = c.getString(c.getColumnIndex(KEY_ADDRESS));
        restaurant.name = c.getString(c.getColumnIndex(KEY_NAME));

        return restaurant;
    }

    public List<Restaurant> getAllRestaurantList() {
        List<Restaurant> restaurantsArrayList = new ArrayList<Restaurant>();

        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANTS;
        Log.d(TAG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                Restaurant restaurants = new Restaurant();
                restaurants.id = c.getInt(c.getColumnIndex(KEY_ID));
                restaurants.address = c.getString(c
                        .getColumnIndex(KEY_ADDRESS));
                restaurants.name = c.getString(c.getColumnIndex(KEY_NAME));

                // adding to Restaurant list
                restaurantsArrayList.add(restaurants);
            } while (c.moveToNext());
        }

        return restaurantsArrayList;
    }
}
