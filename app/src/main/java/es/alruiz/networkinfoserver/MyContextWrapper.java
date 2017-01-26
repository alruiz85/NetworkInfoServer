package es.alruiz.networkinfoserver;

import android.content.Context;
import android.content.ContextWrapper;
import android.provider.Settings;

/**
 * Created by AlfonsoRuiz on 26/01/2017.
 */

public class MyContextWrapper extends ContextWrapper {

    public MyContextWrapper(Context base) {
        super(base);
    }

    public boolean isAirplaneModeOn() {
        return Settings.System.getInt(getContentResolver(),
                Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }
}