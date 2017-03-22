package com.example.xing.smartgarden;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import java.io.IOException;

public class BG_Service extends IntentService {

    public static final String TAG = "myService";
    public static final String MESSAGE = "myMessage";
    public static final String PAYLOAD = "myPayload";
    private String response;
    public BG_Service() {
        super("myService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: " + uri.toString());
        try {
            response= access_Http.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Intent messageIntent = new Intent(MESSAGE);
        messageIntent.putExtra(PAYLOAD, response);
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}
