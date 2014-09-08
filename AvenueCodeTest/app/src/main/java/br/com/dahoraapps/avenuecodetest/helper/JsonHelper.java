package br.com.dahoraapps.avenuecodetest.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;

public class JsonHelper extends AsyncTask<JsonMethod, Void, Object> {

    private JsonMethod method;
    private Context context;
    private ActionBarActivity activity;
    private AsyncTaskCompleteListener callback;

    // Thread methods
    public JsonHelper(Context context, AsyncTaskCompleteListener callback) {
        this.activity = (ActionBarActivity) context;
        this.context = context;
        this.callback = callback;
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
        // Start Progress Bar Indeterminate
        this.activity.setSupportProgressBarIndeterminateVisibility(true);
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        // Calls activity callback
        this.callback.onTaskComplete(result, method);
        // Stop Progress Bar Indeterminate
        this.activity.setSupportProgressBarIndeterminateVisibility(false);
    }

}
