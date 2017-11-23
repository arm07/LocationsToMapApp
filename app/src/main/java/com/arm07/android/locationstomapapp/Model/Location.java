package com.arm07.android.locationstomapapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rashmi on 11/22/2017.
 */

public class Location implements Parcelable {


    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String arrivalTime;

    public Location(int id, String name, double latitude, double longitude, String address, String arrivalTime) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.arrivalTime = arrivalTime;
    }

    protected Location(Parcel in) {
        id = in.readInt();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
        arrivalTime = in.readString();
    }
    public static final Creator<Location> CREATOR = new Creator<Location>(){
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(address);
        parcel.writeString(arrivalTime);
    }
}
