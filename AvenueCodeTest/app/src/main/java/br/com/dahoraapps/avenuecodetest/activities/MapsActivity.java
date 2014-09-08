package br.com.dahoraapps.avenuecodetest.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.dahoraapps.avenuecodetest.R;
import br.com.dahoraapps.avenuecodetest.data.MapsResponse;
import br.com.dahoraapps.avenuecodetest.data.Place;
import br.com.dahoraapps.avenuecodetest.data.Position;

public class MapsActivity extends ActionBarActivity {

    private Place place;
    private MapsResponse response;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra("place")) {
            place = (Place) getIntent().getSerializableExtra("place");
        }
        if (getIntent().hasExtra("maps_response")) {
            response = (MapsResponse) getIntent().getSerializableExtra("maps_response");
        }
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        if (place != null && response != null) {
            for (Place p : response.getResults()) {
                Position pos = p.getGeometry().getLocation();
                mMap.addMarker(new MarkerOptions().position(new LatLng(pos.getLat(), pos.getLng())).title(p.getFormattedAddress()));
            }
        }

        //Center the camera on selected location and zoom
        Position pos = place.getGeometry().getLocation();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(pos.getLat(), pos.getLng()))         // Sets the center of the map to Mountain View
                .zoom(10)                                               // Sets the zoom
//                .bearing(90)                                            // Sets the orientation of the camera to east
//                .tilt(30)                                               // Sets the tilt of the camera to 30 degrees
                .build();                                               // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
