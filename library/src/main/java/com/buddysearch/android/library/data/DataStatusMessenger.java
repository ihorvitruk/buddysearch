package com.buddysearch.android.library.data;

public interface DataStatusMessenger {
    /**
     * Show a message about network connection problem
     */
    void showNoNetworkMessage();

    /**
     * Show a message that data was loaded from cache
     */
    void showFromCacheMessage();
}
