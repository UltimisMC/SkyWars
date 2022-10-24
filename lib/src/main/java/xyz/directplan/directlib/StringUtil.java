package xyz.directplan.directlib;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class StringUtil {

    // Roman literals and values
    private static final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    private static final DecimalFormat readableNumberPattern = new DecimalFormat("#,###");

    private static final String NMS_VERSION;

    static {
        String v = Bukkit.getServer().getClass().getPackage().getName();
        NMS_VERSION = v.substring(v.lastIndexOf('.') + 1);
    }

    public static String getNMSVersion(){
        return NMS_VERSION;
    }


    public static void debug(String func, String msg) {
        System.out.println("[" + func + "]: " + msg);
    }
    // You can use %f for a new line
    /* DirectPlan, All rights reserved. */
    public static List<String> getCorrectDescription(ChatColor color, String description, int charsPerSpace) {
        char[] chars = description.toCharArray();
        List<String> correctDescription = new ArrayList<>();
        int count = 0;
        StringBuilder lineBuilder = new StringBuilder();
//        System.out.println("size: " + description.length());
        for (char c : chars) {
            char aChar = c;
            int lastIndex = description.lastIndexOf(aChar);
            if(lastIndex >= description.length() - 1) {
                lineBuilder.append(aChar);
                correctDescription.add((color == null ? "" : color) + lineBuilder.toString());
//                debug("DONE DESC LIMIT REACHED", "ADDED LINE BUILDER TO LIST: " + lineBuilder);
                // END
                break;
            }
            if ((count >= charsPerSpace) && (aChar == ' ' || aChar == ',' || aChar == '.' || aChar == '!')) {

                correctDescription.add((color == null ? "" : color) + lineBuilder.toString());
//                debug("DONE DESC", "ADDED LINE BUILDER TO LIST: " + lineBuilder);
                count = 0;
                lineBuilder = new StringBuilder();
//                debug("DONE DESC", "LINE BUILDER IS NOW NEW STRING BUILDER");
                continue;
            }
            if (count == 0 && aChar == ' ') {
                aChar = 0;
            }
            count++;
            lineBuilder.append(aChar);
//            debug("CORRECT DESC", "Line builder has appended char (" + aChar + ") (" + count + ")");
        }
        return correctDescription;
    }

    public static String getCorrectDescriptionStr(String delimiter, ChatColor color, String description, int charsPerSpace) {
        List<String> correctDescription = getCorrectDescription(color, description, charsPerSpace);

        return String.join(delimiter, correctDescription);
    }

    public static String getProgressBar(double current, int max, int bars, String symbol, String[] progressColors){

        float percent = (float) (current / max);
        int progressBars = (int) (bars * percent);
        int leftOver = (bars - progressBars);
        String completedColor = progressColors[0]; // PROGRESS IS STABLE
        if(percent <= 0.7){
            completedColor = progressColors[1]; // PROGRESS IS UN-STABLE
        }
        if(percent <= 0.4){
            completedColor = progressColors[2]; // PROGRESS FATAL
        }
        String uncompletedColor = progressColors[3]; // UNCOMPLETED PROGRESS COLOR
        StringBuilder sb = new StringBuilder();
        assert completedColor != null;
        sb.append(ChatColor.translateAlternateColorCodes('&', completedColor));
        for (int i = 0; i < progressBars; i++) {
            sb.append(symbol);
        }
        sb.append(ChatColor.translateAlternateColorCodes('&', uncompletedColor));
        for (int i = 0; i < leftOver; i++) {
            sb.append(symbol);
        }
        return sb.toString();
    }

    public static String getRomanLevel(int number) {
        StringBuilder roman = new StringBuilder();
        int tempNumber = number;
        for(int i = 0; i < values.length; i++) {
            if(tempNumber == 0) break;
            while(tempNumber >= values[i]) {
                tempNumber -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    public static <T> boolean isInBound(List<T> list, int index) {
        return list.size() >= index;
    }

    public static String getReadableNumber(int number) {
        return readableNumberPattern.format(number);
    }
}
