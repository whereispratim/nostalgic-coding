# Valid Palindrome

## Problem Statement

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

## Examples

### Example 1:

Input: `s = "A man, a plan, a canal: Panama"`
Output: `true`
Explanation: "amanaplanacanalpanama" is a palindrome.

### Example 2:

Input: `s = "race a car"`
Output: `false`
Explanation: "raceacar" is not a palindrome.

## Approach

1. Convert the string to lowercase.
2. Remove all non-alphanumeric characters.
3. Use two pointers, one at the start and one at the end of the string.
4. Move the pointers towards the center, comparing characters at each step.
5. If all characters match, it's a palindrome; otherwise, it's not.

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
    def isPalindrome(self, s: str) -> bool:
        # Convert to lowercase and remove non-alphanumeric characters
        s = ''.join(c.lower() for c in s if c.isalnum())

        # Check if it's a palindrome
        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1
        return True
solution = Solution()
print(solution.isPalindrome("A man, a plan, a canal: Panama"))  # Output: True
print(solution.isPalindrome("race a car"))  # Output: False
</code></pre>
</td>
<td>
<pre><code class="java">
class Solution {
    public boolean isPalindrome(String s) {
        // Convert to lowercase and remove non-alphanumeric characters
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");

        // Check if it's a palindrome
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));  // Output: true
        System.out.println(solution.isPalindrome("race a car"));  // Output: false
    }
}
</code></pre>
</td>
</tr>
</table>

## Step-by-Step Walkthrough

Let's walk through the solution using the first example: "A man, a plan, a canal: Panama"

1. Convert to lowercase and remove non-alphanumeric characters:
   "amanaplanacanalpanama"

2. Initialize two pointers:
   left = 0 (pointing to the first 'a')
   right = 20 (pointing to the last 'a')

3. Compare characters and move pointers:
    - 'a' == 'a', move pointers: left = 1, right = 19
    - 'm' == 'm', move pointers: left = 2, right = 18
    - 'a' == 'a', move pointers: left = 3, right = 17
    - 'n' == 'n', move pointers: left = 4, right = 16
    - 'a' == 'a', move pointers: left = 5, right = 15
    - 'p' == 'p', move pointers: left = 6, right = 14
    - 'l' == 'l', move pointers: left = 7, right = 13
    - 'a' == 'a', move pointers: left = 8, right = 12
    - 'n' == 'n', move pointers: left = 9, right = 11
    - 'a' == 'a', move pointers: left = 10, right = 10

4. The pointers have met in the middle without finding any mismatches, so the string is a palindrome.

For the second example, "race a car":

1. Convert to lowercase and remove non-alphanumeric characters:
   "raceacar"

2. Initialize two pointers:
   left = 0 (pointing to 'r')
   right = 7 (pointing to 'r')

3. Compare characters and move pointers:
    - 'r' == 'r', move pointers: left = 1, right = 6
    - 'a' == 'a', move pointers: left = 2, right = 5
    - 'c' != 'c', return false

The function returns false as soon as it finds a mismatch.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string. We traverse the string once to process it and once to check if it's a palindrome.
- Space Complexity: O(n) in the worst case, as we create a new string with all alphanumeric characters. However, this can be optimized to O(1) by skipping non-alphanumeric characters in-place.

## LeetCode Link

[125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)