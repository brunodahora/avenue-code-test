package br.com.dahoraapps.avenuecodetest.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.dahoraapps.avenuecodetest.R;
import br.com.dahoraapps.avenuecodetest.adapter.PlacesAdapter;
import br.com.dahoraapps.avenuecodetest.data.MapsResponse;
import br.com.dahoraapps.avenuecodetest.data.Place;
import br.com.dahoraapps.avenuecodetest.helper.AsyncTaskCompleteListener;
import br.com.dahoraapps.avenuecodetest.helper.JsonExecutor;
import br.com.dahoraapps.avenuecodetest.helper.JsonMethod;
import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity implements AsyncTaskCompleteListener {

    @InjectView(R.id.list)
    ListView listView;
    @InjectView(R.id.empty)
    TextView emptyView;
    private ActionBarActivity activity;
    private PlacesAdapter adapter;
    private MapsResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        activity = this;

        if (savedInstanceState != null && savedInstanceState.containsKey("maps_response")) {
            response = (MapsResponse) savedInstanceState.get("maps_response");
            adapter = new PlacesAdapter(this, response.getResults());
        } else {
            adapter = new PlacesAdapter(this, new ArrayList<Place>());
        }
        listView.setAdapter(adapter);
        listView.setEmptyView(emptyView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(activity, MapsActivity.class);
                intent.putExtra("maps_response", response);
                intent.putExtra("place", (Place) listView.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (response != null)
            outState.putSerializable("maps_response", response);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            JsonExecutor jsonHelper = new JsonExecutor(this, this);
            JsonMethod jsonMethod = JsonMethod.GET_PLACES;
            Bundle bundle = new Bundle();
            bundle.putString("address", intent.getStringExtra(SearchManager.QUERY));
            jsonMethod.setBundle(bundle);
            jsonHelper.execute(new JsonMethod[]{jsonMethod});
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager searchManager =
                    (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                onSearchRequested();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void onTaskComplete(Object result, JsonMethod method) {
        if (method.equals(JsonMethod.GET_PLACES)) {
            response = new Gson().fromJson((String) result, MapsResponse.class);
            if (response != null) {
                List<Place> places = response.getResults();
                adapter.clear();
                if (places != null) {
                    for (Place place : places) {
                        adapter.add(place);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void reload() {

    }
}
