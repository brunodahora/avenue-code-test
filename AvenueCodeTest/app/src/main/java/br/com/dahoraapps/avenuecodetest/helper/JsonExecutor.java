package br.com.dahoraapps.avenuecodetest.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonExecutor extends AsyncTask<JsonMethod, Void, Object> {

    private JsonMethod method;
    private Context context;
    private ActionBarActivity activity;
    private AsyncTaskCompleteListener callback;

    // Thread methods
    public JsonExecutor(Context context, AsyncTaskCompleteListener callback) {
        this.activity = (ActionBarActivity) context;
        this.context = context;
        this.callback = callback;
    }

    // Help Methods
    public static boolean isNetworkAvailable(ConnectivityManager connectivityManager) {
        boolean outcome = false;

        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo tempNetworkInfo : networkInfos) {

            if (tempNetworkInfo.isConnected()) {
                outcome = true;
                break;
            }
        }

        return outcome;
    }

    public static List<JSONObject> getListOfJSONArray(JSONArray values) {
        List<JSONObject> list = new ArrayList<JSONObject>();
        if (values == null) {
            return list;
        } else {
            try {
                for (int i = 0; i < values.length(); i++) {
                    list.add(values.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    @Override
    protected Object doInBackground(JsonMethod... jsonMethods) {
        try {
            method = jsonMethods[0];
            method.setContext(context);
            return method.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.activity.setSupportProgressBarIndeterminate(true);
        this.activity.setSupportProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        this.callback.onTaskComplete(result, method);
        this.activity.setSupportProgressBarIndeterminate(false);
        this.activity.setSupportProgressBarIndeterminateVisibility(false);
    }

}
