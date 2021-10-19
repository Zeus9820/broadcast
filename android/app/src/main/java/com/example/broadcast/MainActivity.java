package com.example.broadcast;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.android.FlutterActivity;
import android.Manifest;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.FlutterEngine;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "samples.flutter.dev/broadcast";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                                android.widget.Toast.makeText(MainActivity.this, "Calling Statement Reached", Toast.LENGTH_SHORT).show();
                                MyReceiver r = new MyReceiver();
                                IntentFilter i = new IntentFilter("android.permission.READ_PHONE_STATE");
                                registerReceiver(r, i);
                                result.success(1);
                        }
                );
    }
}
