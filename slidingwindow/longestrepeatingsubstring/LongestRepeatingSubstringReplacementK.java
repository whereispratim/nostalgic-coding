package longestrepeatingsubstring;
import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingSubstringReplacementK {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        int start = 0;  // Left side of the sliding window
        int maxCount = 0;  // Count of the most frequent character in the current window
        int maxLength = 0;

        // Iterate with the right pointer (end)
        for (int end = 0; end < s.length(); end++) {
            char endChar = s.charAt(end);
            // Update the frequency of the character at the 'end' pointer
            frequencyMap.put(endChar, frequencyMap.getOrDefault(endChar, 0) + 1);

            // Update maxCount with the maximum frequency in the current window
            maxCount = Math.max(maxCount, frequencyMap.get(endChar));

            // Calculate window size and check if we need to shrink it
            int currentWindowSize = end - start + 1;
            if (currentWindowSize - maxCount > k) {
                // If the number of replacements needed exceeds 'k', shrink the window
                char startChar = s.charAt(start);
                frequencyMap.put(startChar, frequencyMap.get(startChar) - 1);
                start++;
            }

            // Update the maximum length found
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingSubstringReplacementK solution = new LongestRepeatingSubstringReplacementK();

        // Test case 1
        String s1 = "AABABBA";
        int k1 = 1;
        System.out.println("Test Case 1: " + solution.characterReplacement(s1, k1));  // Output: 4

        // Test case 2
        String s2 = "ABAB";
        int k2 = 2;
        System.out.println("Test Case 2: " + solution.characterReplacement(s2, k2));  // Output: 4

        // Test case 3
        String s3 = "AAAA";
        int k3 = 2;
        System.out.println("Test Case 3: " + solution.characterReplacement(s3, k3));  // Output: 4
    }
}



