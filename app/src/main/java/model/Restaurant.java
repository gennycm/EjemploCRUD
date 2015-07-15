package model;

/**
 * Created by Lenova-i7quad on 30/06/2015.
 */
public class Restaurant {
    private int mId;
    private String mName;
    private String mAddress;
    private String mDescription;
    private String mPhoneNumber;


    public Restaurant(int id, String name, String address, String description, String phoneNumber) {
        mId = id;
        mName = name;
        mAddress = address;
        mDescription = description;
        mPhoneNumber = phoneNumber;
    }

    public Restaurant() {

    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }
}

