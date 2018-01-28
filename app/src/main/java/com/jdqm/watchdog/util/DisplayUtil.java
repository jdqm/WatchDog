package com.jdqm.watchdog.util;

import android.content.res.Resources;

/**
 * Created by Jdqm on 2018/1/28.
 */

public class DisplayUtil {
    public static int dpToPixel(float dpValue) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * dpValue);
    }
}
