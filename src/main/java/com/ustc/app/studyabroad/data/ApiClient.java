package com.ustc.app.studyabroad.data;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient {

    public static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {
        String baseUrl = null;
        try {
            // Read config.json file from assets
            InputStream inputStream = context.getAssets().open("config.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            // Parse JSON to retrieve BASE_URL
            JSONObject jsonObject = new JSONObject(json);
            baseUrl = jsonObject.getString("base_url");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        if (retrofit == null) {
            assert baseUrl != null;
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
