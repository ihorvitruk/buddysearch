package com.buddysearch.android.library.data.manager;

public interface NetworkManager {

    /**
     * Check network availability
     * @return true if there is network connection and false if not
     */
    boolean isNetworkAvailable();

    /**
     * Enable listening to network availability
     */
    void start();

    /**
     * Disable listening to network availability
     */
    void stop();

    /**
     * Add a listener to network availability
     * @param tag unique id of a listener
     * @param listener network availability listener
     */
    void add(String tag, Listener listener);

    /**
     * Remove a listener to network availability by a unique tag
     * @param tag unique id of a listener
     */
    void remove(String tag);

    /**
     * Network availability listener
     */
    interface Listener {

        /**
         * Is triggered when network connection appears
         */
        void onNetworkAvailable();
    }
}
