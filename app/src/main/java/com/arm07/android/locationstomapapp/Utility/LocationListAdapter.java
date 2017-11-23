package com.arm07.android.locationstomapapp.Utility;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.arm07.android.locationstomapapp.Model.Location;
import com.arm07.android.locationstomapapp.R;

import java.util.List;

/**
 * Created by rashmi on 11/22/2017.
 */

public class LocationListAdapter extends ArrayAdapter {
    private Context context;
    private List<Location> locationList;


    public LocationListAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.locationList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Location location = locationList.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item_view,null);

        TextView tvName = view.findViewById(R.id.name);
        TextView tvAddress = view.findViewById(R.id.address);
        tvName.setText(location.getName());
        tvAddress.setText(location.getAddress());
        return view;
    }

}
