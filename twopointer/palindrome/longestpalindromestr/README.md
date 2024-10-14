# Longest Palindromic Substring

## Problem Statement

Given a string s, return the longest palindromic substring in s.

## Examples

### Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

### Example 2:

Input: s = "cbbd"
Output: "bb"

## Approach

We'll use the "Expand Around Center" approach:
1. Iterate through each character in the string.
2. For each character, consider it as a potential center of a palindrome.
3. Expand outwards from this center, checking for palindromes of both odd and even lengths.
4. Keep track of the longest palindrome found.

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
    def longestPalindrome(self, s: str) -> str:
        if not s:
            return ""

        start = 0
        max_length = 1
        
        def expand_around_center(left: int, right: int) -> int:
            while left >= 0 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return right - left - 1
        
        for i in range(len(s)):
            len1 = expand_around_center(i, i)
            len2 = expand_around_center(i, i + 1)
            length = max(len1, len2)
            
            if length > max_length:
                start = i - (length - 1) // 2
                max_length = length
        
        return s[start:start + max_length]

solution = Solution()
print(solution.longestPalindrome("babad"))  # Output: "bab" or "aba"
print(solution.longestPalindrome("cbbd"))   # Output: "bb"
</code></pre>
</td>
<td>
<pre><code class="java">
public class LongestPalindromeStr {

    // Helper function to expand around the center and return the longest palindrome found
    private String expandAroundCenter(String s, int left, int right) {
        // Expand outward while the characters on both sides are equal
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the palindromic substring found
        return s.substring(left + 1, right);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        String longest = "";

        // Loop through each character in the string as a potential center
        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindrome (single character center)
            String oddPalindrome = expandAroundCenter(s, i, i);
            if (oddPalindrome.length() > longest.length()) {
                longest = oddPalindrome;
            }

            // Even-length palindrome (two-character center)
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > longest.length()) {
                longest = evenPalindrome;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestPalindromeStr solution = new LongestPalindromeStr();

        // Test cases
        System.out.println(solution.longestPalindrome("babad"));  // Output: "bab" or "aba"
        System.out.println(solution.longestPalindrome("cbbd"));   // Output: "bb"
    }
}
</code></pre>
</td>
</tr>
</table>

## Explanation

1. We iterate through each character in the string.
2. For each character, we consider two cases:
    - Odd-length palindromes: The current character is the center.
    - Even-length palindromes: The current character and the next character form the center.
3. We expand around these centers using the `expandAroundCenter` function.
4. This function expands outwards from the center, checking if characters on both sides match.
5. We keep track of the longest palindrome found so far.
6. Finally, we return the substring corresponding to the longest palindrome.


## Detailed Explanation

### 1. longestPalindrome Function Overview:

This function is designed to find the longest palindromic substring in a given string s. It uses the expandAroundCenter method to explore all possible palindromes, starting from each character in the string as the center.

Key Steps:
1. **Edge Case:**
    - If the string is empty (s == null or s.length() == 0), return an empty string immediately.

2. **Iterate through each character:**
    - For each character in the string s, treat it as the center of a palindrome.
    - Two types of centers:
        - Odd-length palindromes: A single character as the center.
        - Even-length palindromes: A pair of consecutive characters as the center.

3. **Expand around each center:**
    - Call expandAroundCenter(s, i, i) to handle odd-length palindromes (centered at one character).
    - Call expandAroundCenter(s, i, i + 1) to handle even-length palindromes (centered at two adjacent characters).

4. **Update the longest palindrome:**
    - Keep track of the longest palindrome found so far by comparing the lengths of palindromic substrings returned by the two calls (odd and even).

5. **Return the longest palindrome.**

### 2. expandAroundCenter Function Overview:

This function expands outward from a center (either a single character or two adjacent characters) and checks if the characters on both sides are equal. If they are, the substring is a palindrome, and the function continues expanding.

Key Steps:
1. **Starting point:**
    - Start with two indices, left and right, which represent the center of the palindrome.

2. **Expand outward:**
    - While the characters at left and right are equal, and left is within the bounds of the string:
        - Expand by decrementing left (left--) and incrementing right (right++).
    - This checks if the substring around the center forms a palindrome.

3. **Return the palindrome:**
    - After expanding as far as possible, return the substring s[left + 1, right]. The left + 1 and right indices are used because the last decrement and increment will overshoot the valid palindrome bounds.

### Walkthrough of longestPalindrome + expandAroundCenter Together:

Let's walk through an example where s = "babad":

1. **Step 1: Initialization:**
    - The string is not empty, so proceed.
    - Initialize variables to store the longest palindrome (start = 0, end = 0).

2. **Step 2: First iteration (i = 0, 'b'):**
    - Odd palindrome:
        - Call expandAroundCenter(s, 0, 0) (centered at 'b').
        - Expands to just 'b', return "b".
    - Even palindrome:
        - Call expandAroundCenter(s, 0, 1) (centered between 'b' and 'a').
        - No expansion, return an empty string.
    - Longest palindrome so far is "b".

3. **Step 3: Second iteration (i = 1, 'a'):**
    - Odd palindrome:
        - Call expandAroundCenter(s, 1, 1) (centered at 'a').
        - Expands to "bab", return "bab".
    - Even palindrome:
        - Call expandAroundCenter(s, 1, 2) (centered between 'a' and 'b').
        - No expansion, return an empty string.
    - Longest palindrome so far is "bab".

4. **Step 4: Third iteration (i = 2, 'b'):**
    - Odd palindrome:
        - Call expandAroundCenter(s, 2, 2) (centered at 'b').
        - Expands to "aba", return "aba".
    - Even palindrome:
        - Call expandAroundCenter(s, 2, 3) (centered between 'b' and 'a').
        - No expansion, return an empty string.
    - Longest palindrome remains "bab" (or "aba", either is acceptable).

5. **Step 5: Fourth iteration (i = 3, 'a'):**
    - Odd palindrome:
        - Call expandAroundCenter(s, 3, 3) (centered at 'a').
        - Expands to just "a", return "a".
    - Even palindrome:
        - Call expandAroundCenter(s, 3, 4) (centered between 'a' and 'd').
        - No expansion, return an empty string.
    - Longest palindrome remains "bab".

6. **Step 6: Fifth iteration (i = 4, 'd'):**
    - Odd palindrome:
        - Call expandAroundCenter(s, 4, 4) (centered at 'd').
        - Expands to just "d", return "d".
    - Even palindrome:
        - No expansion, return an empty string.
    - Longest palindrome remains "bab".

7. **Step 7: Return the result:**
    - After finishing the iterations, return "bab" (or "aba").

### Key Observations:
- The function explores every possible palindrome by treating each character (and pair of characters) as the center.
- It uses efficient expansion logic to avoid unnecessary comparisons, making it run in O(n²) time complexity, where n is the length of the string.

## Complexity Analysis

- Time Complexity: O(n^2), where n is the length of the string. We have n centers and for each center, we expand at most n times.
- Space Complexity: O(1). We only use a constant amount of extra space.

## LeetCode Link

[5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)