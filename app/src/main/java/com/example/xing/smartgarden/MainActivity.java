package com.example.xing.smartgarden;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String JSON_URL =
            "http://16476e72-d82f-41f5-8576-993c64a7d5a2-bluemix.cloudant.com/test/picture/ImageFour";

    private boolean is_Network_Connected;
    private String result,statement;
    TextView output;

    private BroadcastReceiver messageBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(BG_Service.PAYLOAD);
            output.append(message);


        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
        LocalBroadcastManager.getInstance(getApplicationContext()).
                registerReceiver(messageBroadcastReceiver, new IntentFilter(BG_Service.MESSAGE));
        is_Network_Connected = access_Network.hasNetworkAccess(this);
        if(is_Network_Connected){
            result="Connected!";
        }
        else{
            result="not Connected.";
        }
        statement="Network is " + result+"\n\n";
        output.append(statement);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(messageBroadcastReceiver);
    }

    public void runClickHandler(View view) {
        if (is_Network_Connected) {
            Intent intent = new Intent(this, BG_Service.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        } else {
            Toast.makeText(this, "Network not available!", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearClickHandler(View view) {
        output.setText(statement);
    }
}
