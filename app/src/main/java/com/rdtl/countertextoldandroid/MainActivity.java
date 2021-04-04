package com.rdtl.countertextoldandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    BlankFragment blankFragment;

    private static final String ONESIGNAL_APP_ID = "27cdf548-b0cb-4db8-9e69-6e77b07a34a9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blankFragment = new BlankFragment();


        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);




        OneSignal.setNotificationOpenedHandler(new OneSignal.OSNotificationOpenedHandler() {
            @Override
            public void notificationOpened(OSNotificationOpenedResult result) {
//                        String actionId = result.getAction().getActionId();
//                        OSNotificationAction.ActionType type = result.getAction().getType(); // "ActionTaken" | "Opened"
//
//                        String title = result.getNotification().getTitle();
                JSONObject data = result.getNotification().getAdditionalData();
                if(data !=null && data.has("2nd Activity")){


                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);

                }
            /*    if(data !=null && data.has("3rd Activity")){


                    Intent intent = new Intent(MainActivity.this,Activity3.class);
                    startActivity(intent);

                }*/

            }
        });



    }



    private void setFrament(Fragment frament) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.idContainer, frament);
        fragmentTransaction.commit();

    }

}