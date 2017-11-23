package com.arm07.android.locationstomapapp.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.arm07.android.locationstomapapp.Model.Location;
import com.arm07.android.locationstomapapp.R;
import com.arm07.android.locationstomapapp.Utility.LocationListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by rashmi on 11/22/2017.
 */

public class ListFragment extends Fragment {

    private ListView listView;
    private ProgressDialog progressDialog;
    ArrayList<Location> locationList;
    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);
        listView = (ListView)view.findViewById(R.id.lv);
        url = "http://localsearch.azurewebsites.net/api/Locations";
        locationList = new ArrayList<>();
        new LocationsList().execute();
        return view;
    }
    private class LocationsList extends AsyncTask{
        String response;
        @Override
        protected void onPreExecute() {
            Log.i("MYTEST",url);
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading data...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Log.i("MYTEST-pre",url);
        }
        @Override
        protected Object doInBackground(Object[] objects) {
            Log.i("MYTEST2",url);
            JsonArrayRequest js=new JsonArrayRequest(url, new Response.Listener<JSONArray>(){
                @Override
                public void onResponse(JSONArray response) {
                    Log.i("MYTEST-RESP", response.toString());

                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject location = (JSONObject) response.get(i);
                            int id = location.getInt("ID");
                            String name = location.getString("Name");
                            double latitude = location.getDouble("Latitude");
                            double longitude = location.getDouble("Longitude");
                            String address = location.getString("Address");
                            String arrivalTime = location.getString("ArrivalTime");
                            Location currentLocation = new Location(id,name,latitude,longitude,address,arrivalTime);
                            locationList.add(currentLocation);
                        }

                        ListAdapter adapter = new LocationListAdapter(getActivity(), R.layout.list_item_view, locationList);
                        listView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Volley Check", "Error: " + error.getMessage());
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(js);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.i("MYTEST3",url);
            super.onPostExecute(o);
            progressDialog.dismiss();
            progressDialog = null;
            //sortLocation(sortId);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Location location = locationList.get(i);
                            //Toast.makeText(getActivity(), "you selected location "/*+latitude+", "+longitude**/, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), GoogleMapsActivity.class);
                            intent.putExtra("location", location);
                            startActivity(intent);
                        }
                    });
        }

    }
}
