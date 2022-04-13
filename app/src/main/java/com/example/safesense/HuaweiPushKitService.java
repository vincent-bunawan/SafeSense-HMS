package com.example.safesense;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.huawei.hms.push.HmsMessageService;

public class HuaweiPushKitService extends HmsMessageService {
    private static final String TAG = "HuaweiPushKitService";

    @Override
    public void onNewToken(String token, Bundle bundle) {
        // Obtain a push token.
        Log.i(TAG, "have received refresh token " + token);

        // Check whether the token is null.
        if (!TextUtils.isEmpty(token)) {
            refreshedTokenToServer(token);
        }
    }

    private void refreshedTokenToServer(String token) {
        Log.i(TAG, "sending token to server. token:" + token);
    }
}
