package br.com.dahoraapps.avenuecodetest.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class PlacesAdapter extends ArrayAdapter<String> {

    public PlacesAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

}
