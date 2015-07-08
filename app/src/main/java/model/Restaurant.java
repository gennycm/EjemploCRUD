package model;

/**
 * Created by Lenova-i7quad on 30/06/2015.
 */
public class Restaurant {
    public int id;
    public String name;
    public String address;


    public Restaurant(int id, String name, String address) {
        // TODO Auto-generated constructor stub
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Restaurant(){

    }

    public String getName(){
        return this.name;
    }
}
