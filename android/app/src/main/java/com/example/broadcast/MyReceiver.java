package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public Context c;

    public void onReceive(Context context, Intent intent) {
        c = context;
        try {
            TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener();
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
        } catch (Exception e) {
            Toast.makeText(context, "oops!", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyPhoneStateListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                String msg = "New Phone Call Event. Incoming Number: " + incomingNumber;
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(c, msg, duration);
                toast.show();
            }
        }
    }


}
