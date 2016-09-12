package com.buddysearch.android.library.data.manager.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.buddysearch.android.library.data.manager.NetworkManager;

public class NetworkManagerImpl extends BroadcastReceiver implements NetworkManager {

    private Context context;

    private IntentFilter connectivityIntentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

    private Callback callback;

    public NetworkManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void register(Callback callback) {
        //  context.registerReceiver(this, connectivityIntentFilter);
        //  this.callback = callback;

    }

    @Override
    public void unregister() {
        //  context.unregisterReceiver(this);
        // callback = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isNetworkAvailable() && callback != null) {
            if (!isInitialStickyBroadcast()) {
                callback.onNetworkAvailable();
            }
        }
    }
}
