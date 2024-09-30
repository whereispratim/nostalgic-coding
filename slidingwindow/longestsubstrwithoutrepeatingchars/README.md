# Longest Substring Without Repeating Characters

## Problem Description

Given a string `s`, find the length of the longest substring without repeating characters.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class LengthOfLongestSubstring:

    def length_of_longest_substring(self, input_string: str) -> int:
        # Edge case: if the input string is None or empty, return 0
        if input_string is None or len(input_string) == 0:
            return 0

        # A dictionary to store the last index of each character
        last_seen_index_map = {}
        max_length = 0  # To keep track of the maximum length found
        substring_start = 0  # Start index of the current substring

        # Iterate through the characters in the input string
        for end_index in range(len(input_string)):
            current_character = input_string[end_index]

            # If the character has been seen before and is within the current substring
            if current_character in last_seen_index_map:
                # Move the start index to the right of the last seen index
                substring_start = max(substring_start, last_seen_index_map[current_character] + 1)

            # Update the last seen index of the current character
            last_seen_index_map[current_character] = end_index
            # Update the maximum length of the substring found
            max_length = max(max_length, end_index - substring_start + 1)

        return max_length  # Return the length of the longest substring


if __name__ == "__main__":
solution = LengthOfLongestSubstring()
input_string = "abcabcbb"
result = solution.length_of_longest_substring(input_string)
print(f"Length of the longest substring without repeating characters: {result}")

</code></pre>
</td>
<td>
<pre><code class="java">
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

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses the sliding window technique to solve the problem efficiently.

### How It Works (Step-by-Step):

Consider the string: "abcabcbb"

1. Initialization:
   - map = {}
   - maxLength = 0
   - start = 0

2. Iteration 1: end = 0, currentChar = 'a'
   - map does not contain 'a'
   - Update map: map = {'a': 0}
   - Update maxLength: maxLength = 1

3. Iteration 2: end = 1, currentChar = 'b'
   - map does not contain 'b'
   - Update map: map = {'a': 0, 'b': 1}
   - Update maxLength: maxLength = 2

4. Iteration 3: end = 2, currentChar = 'c'
   - map does not contain 'c'
   - Update map: map = {'a': 0, 'b': 1, 'c': 2}
   - Update maxLength: maxLength = 3

5. Iteration 4: end = 3, currentChar = 'a'
   - map contains 'a'
   - Update start: start = max(0, 1) = 1
   - Update map: map = {'a': 3, 'b': 1, 'c': 2}
   - maxLength remains 3

6. Iteration 5: end = 4, currentChar = 'b'
   - map contains 'b'
   - Update start: start = max(1, 2) = 2
   - Update map: map = {'a': 3, 'b': 4, 'c': 2}
   - maxLength remains 3

7. Iteration 6: end = 5, currentChar = 'c'
   - map contains 'c'
   - Update start: start = max(2, 3) = 3
   - Update map: map = {'a': 3, 'b': 4, 'c': 5}
   - maxLength remains 3

8. Iteration 7: end = 6, currentChar = 'b'
   - map contains 'b'
   - Update start: start = max(3, 5) = 5
   - Update map: map = {'a': 3, 'b': 6, 'c': 5}
   - maxLength remains 3

9. Iteration 8: end = 7, currentChar = 'b'
   - map contains 'b'
   - Update start: start = max(5, 7) = 7
   - Update map: map = {'a': 3, 'b': 7, 'c': 5}
   - maxLength remains 3

Output: maxLength = 3

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string, as we traverse the string once and perform constant-time operations for each character.
- Space Complexity: O(min(m, n)), where m is the size of the character set, since we use a hash map to store characters and their indices, which can hold at most min(m, n) entries.
## LeetCode Link

[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)