package com.asa.games.twentyfortyeight;

import com.squareup.otto.Bus;

/**
 * Created by Aaron on 3/25/14.
 */
public class BusProvider {

    private static Bus sInstance;

    public static Bus getInstance() {
        if (sInstance == null) {
            sInstance = new Bus();
        }
        return sInstance;
    }

    public static Bus post(Object object) {
        getInstance().post(object);
        return sInstance;
    }

    public static Bus register(Object o) {
        getInstance().register(o);
        return sInstance;
    }

    public static Bus unregister(Object o) {
        getInstance().unregister(o);
        return sInstance;
    }

}
