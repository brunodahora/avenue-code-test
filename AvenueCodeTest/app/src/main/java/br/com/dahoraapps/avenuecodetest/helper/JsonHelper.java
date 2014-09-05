package br.com.dahoraapps.avenuecodetest.helper;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper extends AsyncTask<JsonMethod, Void, Object> {

    private JsonMethod method;
    private Context context;
    private AsyncTaskCompleteListener callback;
    private Activity activity;

    // Thread methods
    public JsonHelper(Context context, AsyncTaskCompleteListener callback) {
        this.activity = (Activity) context;
        this.context = context;
        this.callback = callback;
    }

    public JsonHelper(Context context, AsyncTaskCompleteListener callback, FragmentActivity activity) {
        this(context, callback);
        this.activity = activity;
    }

    // Help Methods
    public static boolean isNetworkAvailable(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
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
        activity.setProgressBarIndeterminate(true);
        activity.setProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        activity.setProgressBarIndeterminate(false);
        activity.setProgressBarIndeterminateVisibility(false);
        if (!activity.isFinishing()) this.callback.onTaskComplete(result, method);
    }

}
