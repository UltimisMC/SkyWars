package xyz.directplan.directlib;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DirectPlan
 */
public class StringUtil {

    // Roman literals and values
    private static final int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private static final String[] romanLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public static List<String> getCorrectDescription(ChatColor color, String description, int charsPerSpace) {
        char[] chars = description.toCharArray();
        List<String> correctDescription = new ArrayList<>();
        int count = 0;
        StringBuilder lineBuilder = new StringBuilder();
        for (char c : chars) {
            char aChar = c;
            int lastIndex = description.lastIndexOf(aChar);
            if(lastIndex >= description.length() - 1) {
                lineBuilder.append(aChar);
                correctDescription.add((color == null ? "" : color) + lineBuilder.toString());
                break;
            }
            if ((count >= charsPerSpace) && (aChar == ' ' || aChar == ',' || aChar == '.' || aChar == '!')) {

                correctDescription.add((color == null ? "" : color) + lineBuilder.toString());
                count = 0;
                lineBuilder = new StringBuilder();
                continue;
            }
            if (count == 0 && aChar == ' ') {
                aChar = 0;
            }
            count++;
            lineBuilder.append(aChar);
        }
        return correctDescription;
    }

    public static String getCorrectDescriptionStr(String delimiter, ChatColor color, String description, int charsPerSpace) {
        List<String> correctDescription = getCorrectDescription(color, description, charsPerSpace);

        return String.join(delimiter, correctDescription);
    }

    // This can be designed better
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

    private static final ChatColor[] rainbowColors = {ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN,
    ChatColor.AQUA, ChatColor.LIGHT_PURPLE, ChatColor.DARK_PURPLE};

    public static String toRainbow(String text) {
        text = ChatColor.stripColor(text);
        char[] chars = text.toCharArray();
        StringBuilder builder = new StringBuilder();
        int colorIndex = 0;
        for (char aChar : chars) {
            builder.append(rainbowColors[colorIndex]).append(aChar);
            colorIndex++;
            if (colorIndex == rainbowColors.length) colorIndex = 0;
        }
        return builder.toString();
    }

    public static String fixName(String name) {
        name = name.replace("_", " ");
        name = WordUtils.capitalize(name);
        return name;
    }

    public static <T> boolean isWithinRange(List<T> list, int index) {
        return list.size() >= index;
    }

    public static String getReadableNumber(int number) {
        return String.format("%,d", number);
    }
}
