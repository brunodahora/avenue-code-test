package br.com.dahoraapps.avenuecodetest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.dahoraapps.avenuecodetest.data.Place;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class PlacesAdapter extends ArrayAdapter<Place> {

    @InjectView(android.R.id.text1)
    TextView text;

    public PlacesAdapter(Context context, List<Place> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        ButterKnife.inject(this, view);
        Place place = getItem(position);
        text.setText(place.getFormattedAddress());
        return view;
    }
}
