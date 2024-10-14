## Longest Palindrome

### Problem Statement

Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.

Letters are case sensitive, for example, "Aa" is not considered a palindrome.

### Examples

#### Example 1:

Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

#### Example 2:

Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.

### Approach

We'll use a hash map (or array) to count the frequency of each character:
1. Count the frequency of each character in the string.
2. For each character with even count, add the count to the palindrome length.
3. For characters with odd count, add count-1 to the palindrome length.
4. If there's at least one character with odd count, add 1 to the palindrome length (for the center character).

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class Solution:
    def longestPalindrome(self, s: str) -> int:
        char_count = {}
        for char in s:
            char_count[char] = char_count.get(char, 0) + 1

        length = 0
        odd_count = 0
        for count in char_count.values():
            if count % 2 == 0:
                length += count
            else:
                length += count - 1
                odd_count = 1
        
        return length + odd_count

solution = Solution()
print(solution.longestPalindrome("abccccdd"))  # Output: 7
print(solution.longestPalindrome("a"))         # Output: 1
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class Solution {
public int longestPalindrome(String s) {
Map<Character, Integer> charCount = new HashMap<>();
for (char c : s.toCharArray()) {
charCount.put(c, charCount.getOrDefault(c, 0) + 1);
}

        int length = 0;
        boolean hasOdd = false;
        for (int count : charCount.values()) {
            if (count % 2 == 0) {
                length += count;
            } else {
                length += count - 1;
                hasOdd = true;
            }
        }
        
        return length + (hasOdd ? 1 : 0);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("abccccdd"));  // Output: 7
        System.out.println(solution.longestPalindrome("a"));         // Output: 1
    }
}
</code></pre>
</td>
</tr>
</table>

### Explanation

1. We use a hash map (or dictionary in Python) to count the frequency of each character in the string.
2. We iterate through the character counts:
    - If the count is even, we add it entirely to the palindrome length.
    - If the count is odd, we add count-1 to the palindrome length (as we can use all but one of these characters).
3. If we encountered any odd count, we add 1 to the final length (as we can use one odd character as the center of the palindrome).

### Detailed Walkthrough

Let's walk through the solution for s = "abccccdd":

1. Count characters: {'a': 1, 'b': 1, 'c': 4, 'd': 2}
2. Process counts:
    - 'a' (1): length = 0, odd_count = 1
    - 'b' (1): length = 0, odd_count = 1
    - 'c' (4): length = 4, odd_count = 1
    - 'd' (2): length = 6, odd_count = 1
3. Final length: 6 + 1 (for the center) = 7

### Complexity Analysis

- Time Complexity: O(n), where n is the length of the string. We iterate through the string once to count characters and once through the counts.
- Space Complexity: O(1), as there are at most 52 different characters (26 lowercase and 26 uppercase), so the space used by the hash map is constant.

### Key Observations

1. We don't need to actually construct the palindrome, just calculate its potential length.
2. We can use all even-count characters and all but one of each odd-count character.
3. If there's at least one odd-count character, we can use one as the center of the palindrome.

### LeetCode Link

[409. Longest Palindrome](https://leetcode.com/problems/longest-palindrome/)