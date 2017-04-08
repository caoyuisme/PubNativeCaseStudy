package utils;

import android.os.Looper;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by caoyu on 2017/4/8.
 */

public class Utils {

    private static SimpleDateFormat sdfMmDdHhMm = new SimpleDateFormat("MM-dd HH:mm");


    public static boolean isEmptyCollection(Collection collection) {
        if (isNull(collection) || isZero(collection.size())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Object obj) {
        if (obj == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmptyString(String str) {
        if (isNull(str)) {
            return true;
        } else {
            if (isZero(str.trim().length())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean ensureNotNull(Object... objects) {
        for (Object o : objects) {
            if (isNull(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isZero(int num) {
        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMmDdHhMm(long createTime) {
        return String.valueOf(sdfMmDdHhMm.format(new Date(createTime)));
    }
}
