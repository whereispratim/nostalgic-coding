# Minimum Window Substring

## Problem Statement

Given two strings `s` and `t` of lengths `m` and `n` respectively, return the minimum window substring of `s` such that every character in `t` (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

## Examples

### Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

### Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

### Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

## Approach

1. Use two pointers to create a sliding window.
2. Use two hash maps to keep track of the characters in t and the current window.
3. Expand the window to the right until we have all characters from t.
4. Contract the window from the left to find the minimum window.
5. Repeat steps 3-4 until we reach the end of s.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import Counter

class Solution:
def minWindow(self, s: str, t: str) -> str:
if not t or not s:
return ""

        dict_t = Counter(t)
        required = len(dict_t)

        # Filter all the characters from s into a new list along with their index.
        filtered_s = [(i, char) for i, char in enumerate(s) if char in dict_t]

        left = 0
        formed = 0
        window_counts = {}

        ans = float("inf"), None, None

        # Look for the characters only in the filtered list instead of entire s
        for right, char in filtered_s:
            window_counts[char] = window_counts.get(char, 0) + 1

            if window_counts[char] == dict_t[char]:
                formed += 1

            # Try and contract the window till the point where it ceases to be 'desirable'.
            while left <= right and formed == required:
                char_left = filtered_s[left][1]

                # Save the smallest window until now.
                if right - filtered_s[left][0] + 1 < ans[0]:
                    ans = (right - filtered_s[left][0] + 1, filtered_s[left][0], right)

                window_counts[char_left] -= 1
                if window_counts[char_left] < dict_t[char_left]:
                    formed -= 1
                left += 1    

        return "" if ans[0] == float("inf") else s[ans[1] : ans[2] + 1]

solution = Solution()
print(solution.minWindow("ADOBECODEBANC", "ABC"))  # Output: "BANC"
print(solution.minWindow("a", "a"))  # Output: "a"
print(solution.minWindow("a", "aa"))  # Output: ""
</code></pre>
</td>
<td>
<pre><code class="java">
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

</code></pre>
</td>
</tr>
</table>

## Explanation

1. We start by creating a frequency map of characters in string t.
2. We use two pointers, left and right, to create a sliding window.
3. We move the right pointer to expand the window until we have all the required characters from t.
4. Once we have all required characters, we start moving the left pointer to contract the window.
5. We keep track of the minimum window that contains all required characters.
6. We repeat this process until we reach the end of string s.


## Detailed Walkthrough

Let's break down the algorithm and understand how it processes each input step by step.

### Step 1: Build a Frequency Map for t
We first create a frequency map (targetFreq) for string t to count how many of each character we need in our window.
For example, if t = "ABC", then:
targetFreq = {A: 1, B: 1, C: 1}.

### Step 2: Initialize Sliding Window Variables
We initialize pointers for the sliding window (left = 0 and right = 0) and track several variables:
- required is the number of unique characters from t that we need in the window. For t = "ABC", required = 3.
- formed counts how many unique characters from t have the required frequency in the current window.
- windowFreq is another frequency map to track characters in the current sliding window.
- result is an array that keeps track of the smallest valid window so far, storing its length, left, and right pointers.

### Step 3: Slide the Right Pointer
We iterate through s using the right pointer, expanding the window:
- For each character c = s.charAt(right), add it to windowFreq.
- If the frequency of c in windowFreq matches its frequency in targetFreq, increment formed.

Example 1: Input: s = "ADOBECODEBANC", t = "ABC"
- At right = 0 (s.charAt(0) = 'A'), windowFreq = {A: 1}. Since A is in t and its frequency matches, formed = 1.
- At right = 3 (s.charAt(3) = 'B'), windowFreq = {A: 1, B: 1}. Now, formed = 2.
- At right = 5 (s.charAt(5) = 'C'), windowFreq = {A: 1, B: 1, C: 1}. Now, formed = 3, meaning we have a valid window containing all characters from t.

### Step 4: Contract the Window
Once we have a valid window (formed == required), we start moving the left pointer to shrink the window while maintaining its validity:
- At left = 0, we try to remove 'A', which reduces its frequency in windowFreq and causes the window to become invalid (formed = 2).
- We keep track of the smallest valid window during this process. For this example, the window "BANC" (from left = 9 to right = 12) is the smallest valid window.

### Step 5: Expand the Window
After shrinking the window, continue expanding by moving the right pointer again to find the next valid window.

### Step 6: Return the Result
Finally, return the smallest valid window found, or return an empty string if no valid window exists.

### Edge Cases:
1. Input s = "a", t = "a"
   The entire string s matches t, so the output is "a".

2. Input s = "a", t = "aa"
   Since s does not contain enough 'a's to match t, the output is an empty string "".

## Time and Space Complexity

### Time Complexity:
O(m + n), where m is the length of s and n is the length of t. Each character in s is processed at most twice (once by the right pointer and once by the left pointer).

### Space Complexity:
O(m + n), due to the storage of the frequency maps.

## LeetCode Link

[76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)