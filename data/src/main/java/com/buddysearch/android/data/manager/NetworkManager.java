package com.buddysearch.android.data.manager;

public interface NetworkManager {

    boolean isNetworkAvailable();

    void register(Callback callback);

    void unregister();

    interface Callback {
        void onNetworkAvailable();
    }
}
