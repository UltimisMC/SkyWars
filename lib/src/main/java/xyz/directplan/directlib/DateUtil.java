package xyz.directplan.directlib;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Taken from Stack Overflow */
public class DateUtil
{
    private static Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);

    public static String removeTimePattern(String input) { return timePattern.matcher(input).replaceFirst("").trim(); }


    public static long parseDateDiff(String time, boolean future) throws Exception {
        Matcher m = timePattern.matcher(time);
        int years = 0;
        int months = 0;
        int weeks = 0;
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        boolean found = false;
        while (m.find()) {
            if (m.group() != null && !m.group().isEmpty()) {
                for (int c = 0; c < m.groupCount(); c++) {
                    if (m.group(c) != null && !m.group(c).isEmpty()) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    continue;
                }
                if (m.group(1) != null && !m.group(1).isEmpty()) {
                    years = Integer.parseInt(m.group(1));
                }
                if (m.group(2) != null && !m.group(2).isEmpty()) {
                    months = Integer.parseInt(m.group(2));
                }
                if (m.group(3) != null && !m.group(3).isEmpty()) {
                    weeks = Integer.parseInt(m.group(3));
                }
                if (m.group(4) != null && !m.group(4).isEmpty()) {
                    days = Integer.parseInt(m.group(4));
                }
                if (m.group(5) != null && !m.group(5).isEmpty()) {
                    hours = Integer.parseInt(m.group(5));
                }
                if (m.group(6) != null && !m.group(6).isEmpty()) {
                    minutes = Integer.parseInt(m.group(6));
                }
                if (m.group(7) != null && !m.group(7).isEmpty()) {
                    seconds = Integer.parseInt(m.group(7));
                }

                break;
            }
        }
        if (!found) {
            throw new Exception("Illegal Date");
        }
        GregorianCalendar var13 = new GregorianCalendar();
        if (years > 0) {
            var13.add(Calendar.YEAR, years * (future ? 1 : -1));
        }
        if (months > 0) {
            var13.add(Calendar.MONTH, months * (future ? 1 : -1));
        }
        if (weeks > 0) {
            var13.add(Calendar.WEEK_OF_YEAR, weeks * (future ? 1 : -1));
        }
        if (days > 0) {
            var13.add(Calendar.DATE, days * (future ? 1 : -1));
        }
        if (hours > 0) {
            var13.add(Calendar.HOUR_OF_DAY, hours * (future ? 1 : -1));
        }
        if (minutes > 0) {
            var13.add(Calendar.MINUTE, minutes * (future ? 1 : -1));
        }
        if (seconds > 0) {
            var13.add(Calendar.SECOND, seconds * (future ? 1 : -1));
        }
        GregorianCalendar max = new GregorianCalendar();
        max.add(Calendar.YEAR, 10);
        return var13.after(max) ? max.getTimeInMillis() : var13.getTimeInMillis();
    }

    public static String formatDateDiff(long date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(date);
        GregorianCalendar now = new GregorianCalendar();
        return formatDateDiff(now, c);
    }

    public static String formatSimplifiedDateDiff(long date) {
        GregorianCalendar c = new GregorianCalendar();
        c.setTimeInMillis(date);
        GregorianCalendar now = new GregorianCalendar();
        return formatSimplifiedDateDiff(now, c);
    }

    public static String formatSimplifiedDateDiff(Calendar fromDate, Calendar toDate) {
        boolean future = false;
        if (toDate.equals(fromDate)) {
            return "now";
        }
        if (toDate.after(fromDate)) {
            future = true;
        }
        StringBuilder sb = new StringBuilder();
        int[] types = { 1, 2, 5, 11, 12, 13 };
        String[] names = { "y", "y", "m", "m", "d", "d", "h", "h", "m", "m", "s", "s" };
        for (int accuracy = 0, i = 0; i < types.length && accuracy <= 2; i++) {
            int diff = dateDiff(types[i], fromDate, toDate, future);
            if (diff > 0) {
                accuracy++;
                sb.append(" ").append(diff).append(names[i * 2 + ((diff > 1) ? 1 : 0)]);
            }
        }
        return (sb.length() == 0) ? "now" : sb.toString().trim();
    }

    @Deprecated
    public static String readableTimeLimit(long time) {
        if(time == -1L) {
            return "Permanent";
        }
        return "N/A";
    }
    public static String readableTime(long time) {
        return readableTime(time, false);
    }

    public static String readableTime(long time, boolean limit) {
        if(time == -1L) {
            return "Permanent";
        }
        long SECOND = 1000;
        long MINUTE = 60000;
        long HOUR = 3600000;
        long DAY = 86400000;
        long ms = time;
        StringBuilder text = new StringBuilder();
        int size = 0;
        if (time > DAY) {
            text.append(time / DAY).append("d ");
            ms = time % DAY;
            size++;
        }
        if (ms > HOUR) {
            text.append(ms / HOUR).append("h ");
            ms %= HOUR;
            size++;
        }
        if (ms > MINUTE) {
            text.append(ms / MINUTE).append("m ");
            ms %= MINUTE;
            size++;
        }
        if (ms > SECOND) {
            if(size < 3) {
                text.append(ms / SECOND).append("s ");
            }
        }
        return text.toString().trim();
    }

    public static String readableTime(BigDecimal time) {
        String text = "";
        if (time.doubleValue() <= 60.0D) {
            time = time.add(BigDecimal.valueOf(0.1D));
            return " " + time + "s";
        }
        if (time.doubleValue() <= 3600.0D) {
            int minutes = time.intValue() / 60;
            int seconds = time.intValue() % 60;
            DecimalFormat formatter = new DecimalFormat("00");
            return " " + formatter.format(minutes) + ":" + formatter.format(seconds) + "m";
        }
        return null;
    }



    public static String formatDateDiff(Calendar fromDate, Calendar toDate) {
        boolean future = false;

        if (toDate.equals(fromDate)) {
            return "now";
        }

        if (toDate.after(fromDate)) {
            future = true;
        }

        StringBuilder sb = new StringBuilder();
        int[] types = { 1, 2, 5, 11, 12, 13 };
        String[] names = { "year", "years", "month", "months", "day", "days", "hour", "hours", "minute", "minutes", "second", "seconds" };
        int accuracy = 0;

        for (int i = 0; i < types.length && accuracy <= 2; i++) {
            int diff = dateDiff(types[i], fromDate, toDate, future);

            if (diff > 0) {
                accuracy++;
                sb.append(" ").append(diff).append(" ").append(names[i * 2 + ((diff > 1) ? 1 : 0)]);
            }
        }

        return (sb.length() == 0) ? "now" : sb.toString().trim();
    }


    private static int dateDiff(int type, Calendar fromDate, Calendar toDate, boolean future) {
        int diff = 0;

        long savedDate;

        for (savedDate = fromDate.getTimeInMillis(); (future && !fromDate.after(toDate)) || (!future && !fromDate.before(toDate)); diff++) {
            savedDate = fromDate.getTimeInMillis();
            fromDate.add(type, future ? 1 : -1);
        }

        diff--;
        fromDate.setTimeInMillis(savedDate);
        return diff;
    }

    public static Long parseTime(String time) {
        if (time.equalsIgnoreCase("permanent") || time.equalsIgnoreCase("perm")) {
            return -1L;
        }

        long totalTime = 0L;
        boolean found = false;
        Matcher matcher = Pattern.compile("\\d+\\D+").matcher(time);

        while (matcher.find()) {
            String s = matcher.group();
            long value = Long.parseLong(s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[0]);
            String type = s.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)")[1];
            String str;
            switch ((str = type).hashCode()) { case 77: if (!str.equals("M")) {
                continue;
            }

                totalTime += value * 60L * 60L * 24L * 30L;
                found = true;case 100: if (!str.equals("d")) continue;  totalTime += value * 60L * 60L * 24L; found = true;case 104: if (!str.equals("h")) continue;  totalTime += value * 60L * 60L; found = true;case 109: if (!str.equals("m")) continue;  totalTime += value * 60L; found = true;case 115: if (!str.equals("s"))
                continue;  totalTime += value; found = true;case 119: if (!str.equals("w"))
                continue;  totalTime += value * 60L * 60L * 24L * 7L; found = true;case 121: if (!str.equals("y"))
                continue;  totalTime += value * 60L * 60L * 24L * 365L;
                found = true; }



        }
        return !found ? 1L : totalTime * 1000L;
    }


    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

    public static String getFormattedDate(long date) {
        return simpleDateFormat.format(new Date(date));
    }

}