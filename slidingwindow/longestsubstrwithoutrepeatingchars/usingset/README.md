# Longest Substring Without Repeating Characters

## Problem Statement

Given a string `s`, find the length of the longest substring without repeating characters.

## Examples

### Example 1:

Input: `s = "abcabcbb"`
Output: `3`
Explanation: The answer is "abc", with the length of 3.

### Example 2:

Input: `s = "bbbbb"`
Output: `1`
Explanation: The answer is "b", with the length of 1.

### Example 3:

Input: `s = "pwwkew"`
Output: `3`
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

## Approach

1. Use a sliding window approach with two pointers: start and end.
2. Use a set to keep track of characters in the current window.
3. Expand the window by moving the end pointer to the right.
4. If a repeating character is found, contract the window from the left.
5. Keep track of the maximum length of the window.

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
    def lengthOfLongestSubstring(self, s: str) -> int:
        char_set = set()
        max_length = 0
        start = 0

        for end in range(len(s)):
            while s[end] in char_set:
                char_set.remove(s[start])
                start += 1
            char_set.add(s[end])
            max_length = max(max_length, end - start + 1)
        
        return max_length

solution = Solution()
print(solution.lengthOfLongestSubstring("abcabcbb"))  # Output: 3
print(solution.lengthOfLongestSubstring("bbbbb"))     # Output: 1
print(solution.lengthOfLongestSubstring("pwwkew"))    # Output: 3
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class Solution {
public int lengthOfLongestSubstring(String s) {
Set<Character> charSet = new HashSet<>();
int maxLength = 0;
int start = 0;

        for (int end = 0; end < s.length(); end++) {
            while (charSet.contains(s.charAt(end))) {
                charSet.remove(s.charAt(start));
                start++;
            }
            charSet.add(s.charAt(end));
            maxLength = Math.max(maxLength, end - start + 1);
        }
        
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));  // Output: 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));     // Output: 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));    // Output: 3
    }
}
</code></pre>
</td>
</tr>
</table>

## Step-by-Step Walkthrough

Let's walk through the solution using the first example: "abcabcbb"

1. Initialize:
    - char_set = {}
    - max_length = 0
    - start = 0
    - end = 0

2. Iterate through the string:
    - end = 0, s[end] = 'a'
        - Add 'a' to char_set
        - Update max_length = 1
    - end = 1, s[end] = 'b'
        - Add 'b' to char_set
        - Update max_length = 2
    - end = 2, s[end] = 'c'
        - Add 'c' to char_set
        - Update max_length = 3
    - end = 3, s[end] = 'a'
        - 'a' is already in char_set
        - Remove 'a' from char_set, start = 1
        - Add 'a' to char_set
        - max_length remains 3
    - end = 4, s[end] = 'b'
        - 'b' is already in char_set
        - Remove 'b' from char_set, start = 2
        - Add 'b' to char_set
        - max_length remains 3
    - end = 5, s[end] = 'c'
        - 'c' is already in char_set
        - Remove 'c' from char_set, start = 3
        - Add 'c' to char_set
        - max_length remains 3
    - end = 6, s[end] = 'b'
        - Add 'b' to char_set
        - max_length remains 3
    - end = 7, s[end] = 'b'
        - 'b' is already in char_set
        - Remove 'b' from char_set, start = 6
        - Add 'b' to char_set
        - max_length remains 3

3. Return max_length = 3

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string. In the worst case, each character will be visited twice by the end pointer.
- Space Complexity: O(min(m, n)), where m is the size of the character set. In the worst case, the whole string might be unique characters.

## LeetCode Link

[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)