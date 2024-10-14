# Palindromic Substrings

## Problem Statement

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

## Examples

### Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

### Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

## Approach

We'll use the "Expand Around Center" approach:
1. Iterate through each character in the string.
2. For each character, consider it as a potential center of a palindrome.
3. Expand outwards from this center, counting palindromes of both odd and even lengths.
4. Keep a running total of all palindromes found.

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
    def countSubstrings(self, s: str) -> int:
        count = 0

        def expandAroundCenter(left: int, right: int) -> int:
            subcount = 0
            while left >= 0 and right < len(s) and s[left] == s[right]:
                subcount += 1
                left -= 1
                right += 1
            return subcount
        
        for i in range(len(s)):
            count += expandAroundCenter(i, i)  # Odd-length palindromes
            count += expandAroundCenter(i, i + 1)  # Even-length palindromes
        
        return count

solution = Solution()
print(solution.countSubstrings("abc"))  # Output: 3
print(solution.countSubstrings("aaa"))  # Output: 6
</code></pre>
</td>
<td>
<pre><code class="java">
public class NoOfPalindromeSubStr {
    public int countSubstrings(String s) {
        int count = 0;

        // Expand around each character (for odd-length palindromes)
        // and around each pair of adjacent characters (for even-length palindromes).
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes centered at i
            count += expandAroundCenter(s, i, i);

            // Count even-length palindromes centered between i and i + 1
            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }

    // Helper function to expand around a center and count palindromic substrings
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;

        // Expand outward while the characters at left and right are equal and within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;  // A new palindrome is found
            left--;   // Move left pointer outward
            right++;  // Move right pointer outward
        }

        return count;
    }

    public static void main(String[] args) {
        NoOfPalindromeSubStr sol = new NoOfPalindromeSubStr();

        // Test cases
        System.out.println(sol.countSubstrings("abc"));   // Output: 3 ("a", "b", "c")
        System.out.println(sol.countSubstrings("aaa"));   // Output: 6 ("a", "a", "a", "aa", "aa", "aaa")
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
4. This function expands outwards from the center, counting palindromes as it goes.
5. We keep a running total of all palindromes found.

## Detailed Walkthrough

Let's walk through the solution for s = "aaa":

1. Start with i = 0 (first 'a'):
    - Odd-length: expandAroundCenter(0, 0)
        - "a" is a palindrome, count = 1
    - Even-length: expandAroundCenter(0, 1)
        - "aa" is a palindrome, count = 2

2. Move to i = 1 (second 'a'):
    - Odd-length: expandAroundCenter(1, 1)
        - "a" is a palindrome, count = 3
    - Even-length: expandAroundCenter(1, 2)
        - "aa" is a palindrome, count = 4

3. End with i = 2 (third 'a'):
    - Odd-length: expandAroundCenter(2, 2)
        - "a" is a palindrome, count = 5
    - Even-length: expandAroundCenter(2, 3)
        - No palindrome found (out of bounds)

4. Final step:
    - The entire string "aaa" is also a palindrome, count = 6

Therefore, the total count of palindromic substrings is 6.

## Complexity Analysis

- Time Complexity: O(n^2), where n is the length of the string. We have n centers and for each center, we expand at most n times.
- Space Complexity: O(1). We only use a constant amount of extra space.

## LeetCode Link

[647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/)