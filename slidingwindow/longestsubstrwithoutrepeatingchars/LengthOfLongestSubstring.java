package longestsubstrwithoutrepeatingchars;

import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String inputString) {
        // Edge case: if the input string is null or empty, return 0
        if (inputString == null || inputString.length() == 0) return 0;

        // A map to store the last index of each character
        Map<Character, Integer> lastSeenIndexMap = new HashMap<>();
        int maxLength = 0; // To keep track of the maximum length found
        int substringStart = 0; // Start index of the current substring

        // Iterate through the characters in the input string
        for (int endIndex = 0; endIndex < inputString.length(); endIndex++) {
            char currentCharacter = inputString.charAt(endIndex);

            // If the character has been seen before and is within the current substring
            if (lastSeenIndexMap.containsKey(currentCharacter)) {
                // Move the start index to the right of the last seen index
                substringStart = Math.max(substringStart, lastSeenIndexMap.get(currentCharacter) + 1);
            }

            // Update the last seen index of the current character
            lastSeenIndexMap.put(currentCharacter, endIndex);
            // Update the maximum length of the substring found
            maxLength = Math.max(maxLength, endIndex - substringStart + 1);
        }

        return maxLength; // Return the length of the longest substring
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        String input = "abcabcbb";
        int result = solution.lengthOfLongestSubstring(input);
        System.out.println("Length of the longest substring without repeating characters: " + result);
    }
}
