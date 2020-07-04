import java.util.*;

public class Challenge {

    public static void main(String[] args) {
        System.out.println(firstNonRepeatingLetter("SSSSStSSSS"));
    }

    public static String firstNonRepeatingLetter(String str) {
        Map<String, Integer> strMap = new LinkedHashMap<>();

        // Splitting the strut string to individual characters
        String[] ch = str.split("");
        String result = "";

        // looping on each character to add it to the map
        for (int a = 0; a < str.length(); a++) {
            String alpha = ch[a];
            // Upper case version of the strut char
            String Upperalpha = alpha.toUpperCase();
            // lower case version of the strut char
            String Loweralpha = alpha.toLowerCase();
            int value = 0;

            // Check if any form of the char is in map, if present then increase the value
            if (strMap.containsKey(Upperalpha) || strMap.containsKey(Loweralpha)) {
                try {
                    value = strMap.get(Upperalpha);
                    value++;
                    strMap.put(Upperalpha, value);
                } catch (Exception e) {
                    value = strMap.get(Loweralpha);
                    value++;
                    strMap.put(Loweralpha, value);
                }

            } else {
                // Add the char to the map with value 1 if not already present
                strMap.put(alpha, 1);
            }

        }

        // Loop over the map to print the first character which occurs once
        for (Map.Entry<String, Integer> entry : strMap.entrySet()) {
            if (entry.getValue() == 1) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }
}
