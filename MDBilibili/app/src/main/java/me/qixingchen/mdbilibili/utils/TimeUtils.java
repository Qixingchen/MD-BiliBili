package me.qixingchen.mdbilibili.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yulan on 2016/1/21.
 */
public class TimeUtils {
    public static String getReadableTime(long unixTime) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar compare = Calendar.getInstance(Locale.CHINA);
        compare.setTime(new Date(unixTime));

        long dayDiff = now.getTime().getTime() / TimeUnit.DAYS.toMillis(1)
                - compare.getTime().getTime() / TimeUnit.DAYS.toMillis(1);
        String ans = "";

        if (dayDiff != 0) {
            if (dayDiff == 1) {
                ans += "昨日";
            } else if (dayDiff == 2) {
                ans += "前日";
            } else {
                if (now.get(Calendar.YEAR) == compare.get(Calendar.YEAR)) {
                    ans += new SimpleDateFormat("M月d日", Locale.CHINA).format(new Date(unixTime));
                } else {
                    ans += new SimpleDateFormat("YY年M月d日", Locale.CHINA).format(new Date(unixTime));
                }
            }
        }
        ans += new SimpleDateFormat(" HH:mm", Locale.CHINA).format(new Date(unixTime));
        return ans;
    }
}
