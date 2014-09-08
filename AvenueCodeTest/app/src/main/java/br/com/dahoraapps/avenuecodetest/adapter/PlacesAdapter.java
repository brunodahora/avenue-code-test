package br.com.dahoraapps.avenuecodetest.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import br.com.dahoraapps.avenuecodetest.data.Place;

public class PlacesAdapter extends ArrayAdapter<Place> {

    public PlacesAdapter(Context context, int resource, List<Place> objects) {
        super(context, resource, objects);
    }

}
