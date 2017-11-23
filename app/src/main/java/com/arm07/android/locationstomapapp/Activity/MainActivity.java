package com.arm07.android.locationstomapapp.Activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.arm07.android.locationstomapapp.Model.Location;
import com.arm07.android.locationstomapapp.R;
import com.arm07.android.locationstomapapp.Utility.LocationListAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListLocationSelected {


    private ListView listView;
    private ProgressDialog progressDialog;
    ArrayList<Location> locationList;
    String urlString;
    int sortId = R.id.sortByName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("sortId", sortId);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (sortId=item.getItemId()){
            case R.id.sortByName:
                //Toast.makeText(this, "Sorted by name", Toast.LENGTH_SHORT).show();
                sortLocation(R.id.sortByName);
                listView.setAdapter(new LocationListAdapter(MainActivity.this,R.layout.list_item_view, locationList));
                return true;
            case R.id.sortByDistance:
                sortLocation(R.id.sortByDistance);
                listView.setAdapter(new LocationListAdapter(MainActivity.this,R.layout.list_item_view, locationList));
                return true;
            case R.id.sortByArrivalTime:
                sortLocation(R.id.sortByArrivalTime);
                listView.setAdapter(new LocationListAdapter(MainActivity.this,R.layout.list_item_view, locationList));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void sortLocation(int sortId){
        Comparator<Location> comparator=null;
        switch (sortId){
            case R.id.sortByName:
                comparator = new Comparator<Location>() {
                    @Override
                    public int compare(Location location1, Location location2) {
                        return location1.getName().compareTo(location2.getName());
                    }
                };
                break;
            case R.id.sortByDistance:
                comparator = new Comparator<Location>() {
                    @Override
                    public int compare(Location location1, Location location2) {
                        return location1.getName().compareTo(location2.getName());
                        // return String.valueOf(location1.getDistance()).compareTo(String.valueOf(location2.getDistance()));
                    }
                };
                break;
            case R.id.sortByArrivalTime:
                comparator = new Comparator<Location>() {
                    @Override
                    public int compare(Location location1, Location location2) {
                        return location1.getArrivalTime().compareTo(location2.getArrivalTime());
                    }
                };
        }

        Collections.sort(locationList, comparator);
    }

    @Override
    public void onLocationSelected(int index) {
        Toast.makeText(MainActivity.this,"Location selected!",Toast.LENGTH_SHORT).show();
       // Intent intent=new Intent(this,MapsActivity.class);
        //startActivity(intent);
    }
}
