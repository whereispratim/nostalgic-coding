package minwindowsubstr;
import java.util.HashMap;
import java.util.Map;

class MinWindowSubStr {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }

        // Step 1: Count the frequency of characters in t
        Map<Character, Integer> targetFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetFreq.put(c, targetFreq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Initialize variables
        int left = 0, right = 0;
        int required = targetFreq.size();  // Number of unique characters in t
        int formed = 0;  // How many characters from t are satisfied in the current window
        Map<Character, Integer> windowFreq = new HashMap<>();
        int[] result = {-1, 0, 0};  // {window size, left, right} for the minimum window

        // Step 3: Start sliding window
        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            // If the frequency of current character matches the required frequency, increment formed
            if (targetFreq.containsKey(c) && windowFreq.get(c).intValue() == targetFreq.get(c).intValue()) {
                formed++;
            }

            // Step 4: When we have a valid window, try to shrink it by moving the left pointer
            while (left <= right && formed == required) {
                c = s.charAt(left);

                // Save the smallest window so far
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }

                // Now, try to shrink the window by removing the leftmost character
                windowFreq.put(c, windowFreq.get(c) - 1);
                if (targetFreq.containsKey(c) && windowFreq.get(c).intValue() < targetFreq.get(c).intValue()) {
                    formed--;
                }

                left++;
            }

            // Expand the window
            right++;
        }

        // Step 5: Return the minimum window substring
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    public static void main(String[] args) {
        MinWindowSubStr solution = new MinWindowSubStr();

        // Test cases
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("Minimum window substring: " + solution.minWindow(s1, t1));  // Output: "BANC"

        String s2 = "a";
        String t2 = "a";
        System.out.println("Minimum window substring: " + solution.minWindow(s2, t2));  // Output: "a"

        String s3 = "a";
        String t3 = "aa";
        System.out.println("Minimum window substring: " + solution.minWindow(s3, t3));  // Output: ""
    }
}

