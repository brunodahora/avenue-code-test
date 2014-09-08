package br.com.dahoraapps.avenuecodetest.helper;

import android.content.Context;
import android.os.Bundle;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public enum JsonMethod {
    GET_PLACES {
        @Override
        public Object execute() throws Exception {
            String address = bundle.getString("address");
            return executeGet(MAPS_API_URL + "/json?address=" + address + "&sensor=false");
        }
    };

    public static String MAPS_API_URL = "http://http://maps.googleapis.com/maps/api/geocode/";

    private static Bundle bundle;
    private static Context context;

    private static String readStream(InputStream in) {
        String stream = new String();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                stream += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return stream;
    }

    // GET Methods
    private static String executeGet(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        try {
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setConnectTimeout(60000);
            connection.setReadTimeout(60000);
            if (connection.getResponseCode() == 200) {
                return readStream(new BufferedInputStream(connection.getInputStream()));
            } else {
                return null;
            }
        } finally {
            connection.disconnect();
        }
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        JsonMethod.bundle = bundle;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        JsonMethod.context = context;
    }

    public abstract Object execute() throws Exception;

}
