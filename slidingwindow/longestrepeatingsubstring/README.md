# Longest Repeating Character Replacement

## Problem Statement

You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

## Examples

### Example 1:

Input: `s = "ABAB"`, `k = 2`
Output: `4`
Explanation: Replace the two 'A's with two 'B's or vice versa.

### Example 2:

Input: `s = "AABABBA"`, `k = 1`
Output: `4`
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

## Approach

1. Use a sliding window approach with two pointers: start and end.
2. Use a dictionary to keep track of character frequencies in the current window.
3. Maintain a variable for the character with the maximum frequency in the current window.
4. If the window size minus the max frequency is greater than k, contract the window from the left.
5. Update the max length of the valid substring.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        char_count = {}
        max_count = 0
        max_length = 0
        start = 0

        for end in range(len(s)):
            char_count[s[end]] = char_count.get(s[end], 0) + 1
            max_count = max(max_count, char_count[s[end]])
            
            if end - start + 1 - max_count > k:
                char_count[s[start]] -= 1
                start += 1
            
            max_length = max(max_length, end - start + 1)
        
        return max_length

solution = Solution()
print(solution.characterReplacement("ABAB", 2))    # Output: 4
print(solution.characterReplacement("AABABBA", 1)) # Output: 4
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class Solution {
public int characterReplacement(String s, int k) {
Map<Character, Integer> charCount = new HashMap<>();
int maxCount = 0;
int maxLength = 0;
int start = 0;

        for (int end = 0; end < s.length(); end++) {
            charCount.put(s.charAt(end), charCount.getOrDefault(s.charAt(end), 0) + 1);
            maxCount = Math.max(maxCount, charCount.get(s.charAt(end)));
            
            if (end - start + 1 - maxCount > k) {
                charCount.put(s.charAt(start), charCount.get(s.charAt(start)) - 1);
                start++;
            }
            
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.characterReplacement("ABAB", 2));    // Output: 4
        System.out.println(solution.characterReplacement("AABABBA", 1)); // Output: 4
    }
}
</code></pre>
</td>
</tr>
</table>

## Step-by-Step Walkthrough

Let's walk through the solution using the first example: s = "ABAB", k = 2

1. Initialize:
    - char_count = {}
    - max_count = 0
    - max_length = 0
    - start = 0

2. Iterate through the string:
    - end = 0, s[end] = 'A'
        - char_count = {'A': 1}
        - max_count = 1
        - max_length = 1
    - end = 1, s[end] = 'B'
        - char_count = {'A': 1, 'B': 1}
        - max_count = 1
        - max_length = 2
    - end = 2, s[end] = 'A'
        - char_count = {'A': 2, 'B': 1}
        - max_count = 2
        - max_length = 3
    - end = 3, s[end] = 'B'
        - char_count = {'A': 2, 'B': 2}
        - max_count = 2
        - max_length = 4

3. Return max_length = 4



## Detailed Example and Walkthrough

Let's walk through a detailed example to understand how the algorithm works:

Input:
- s = "AABABBA"
- k = 1 (we can replace at most 1 character)

We aim to find the length of the longest substring where we can replace at most 1 character to make the entire substring consist of the same character.

### Steps and Walkthrough:

1. **Initialization:**
   - We initialize the sliding window with start = 0 and a frequency map to track character counts in the current window.
   - The maxCount keeps track of the maximum frequency of any character in the window.
   - We'll iterate through the string, expanding the window with the end pointer.

2. **Iteration 1: end = 0, character = 'A'**
   - We add 'A' to the frequency map: {'A': 1}
   - The most frequent character count in the current window is maxCount = 1.
   - Current window size: end - start + 1 = 1
   - Since the window size (1) minus maxCount (1) is 0 (no replacements needed), which is less than or equal to k = 1, we don't need to shrink the window.
   - Max length so far: maxLength = 1

3. **Iteration 2: end = 1, character = 'A'**
   - Add 'A' to the frequency map: {'A': 2}
   - The most frequent character count in the window is maxCount = 2.
   - Current window size: end - start + 1 = 2
   - The number of replacements needed is 2 - 2 = 0, which is still less than or equal to k = 1.
   - Max length so far: maxLength = 2

4. **Iteration 3: end = 2, character = 'B'**
   - Add 'B' to the frequency map: {'A': 2, 'B': 1}
   - The most frequent character count is still maxCount = 2 (for 'A').
   - Current window size: end - start + 1 = 3
   - The number of replacements needed is 3 - 2 = 1, which is exactly equal to k = 1.
   - Max length so far: maxLength = 3

5. **Iteration 4: end = 3, character = 'A'**
   - Add 'A' to the frequency map: {'A': 3, 'B': 1}
   - The most frequent character count is maxCount = 3 (for 'A').
   - Current window size: end - start + 1 = 4
   - The number of replacements needed is 4 - 3 = 1, still within the limit k = 1.
   - Max length so far: maxLength = 4

6. **Iteration 5: end = 4, character = 'B'**
   - Add 'B' to the frequency map: {'A': 3, 'B': 2}
   - The most frequent character count is still maxCount = 3 (for 'A').
   - Current window size: end - start + 1 = 5
   - The number of replacements needed is 5 - 3 = 2, which exceeds k = 1.
   - Action: We need to shrink the window from the left by moving the start pointer.
   - Remove the character at start = 0 ('A'), updating the frequency map: {'A': 2, 'B': 2}
   - Move start = 1.

7. **Iteration 6: end = 5, character = 'B'**
   - The current window after shrinking is from start = 1 to end = 5: 'ABABB'.
   - Frequency map: {'A': 2, 'B': 3}
   - The most frequent character count is maxCount = 3 (for 'B').
   - Current window size: end - start + 1 = 5
   - The number of replacements needed is 5 - 3 = 2, which exceeds k = 1.
   - Action: We shrink the window again.
   - Remove the character at start = 1 ('A'), updating the frequency map: {'A': 1, 'B': 3}
   - Move start = 2.

8. **Iteration 7: end = 6, character = 'A'**
   - The current window is now from start = 2 to end = 6: 'BABBA'.
   - Frequency map: {'A': 2, 'B': 3}
   - The most frequent character count is still maxCount = 3 (for 'B').
   - Current window size: end - start + 1 = 5
   - The number of replacements needed is 5 - 3 = 2, which exceeds k = 1.
   - Action: Shrink the window.
   - Remove the character at start = 2 ('B'), updating the frequency map: {'A': 2, 'B': 2}
   - Move start = 3.

### Final Result:
The maximum length of a substring with at most 1 replacement that makes all characters the same is 4 (found during iterations 3 and 4).

### Summary of Key Points:
- The sliding window expands by moving the end pointer and contracts by moving the start pointer.
- The maxCount tracks the frequency of the most frequent character in the current window.
- We shrink the window when the number of replacements needed exceeds k.
- The maximum window length during valid configurations is tracked and returned.

### Final Output:
The length of the longest substring where we can replace at most 1 character is 4.


## Explanation

- We use a sliding window approach to find the longest substring with repeating characters after at most k replacements.
- The char_count dictionary keeps track of the frequency of each character in the current window.
- max_count represents the count of the most frequent character in the current window.
- We expand the window by moving the end pointer to the right.
- If the number of characters to be replaced (window size - max_count) exceeds k, we contract the window from the left.
- We continuously update max_length to keep track of the longest valid substring.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string. We traverse the string once.
- Space Complexity: O(min(m, 26)), where m is the size of the character set. In this case, it's O(26) for uppercase English letters.

## LeetCode Link

[424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)