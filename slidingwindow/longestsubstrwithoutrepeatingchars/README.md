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


### HashMap (charIndexMap):

Stores characters as keys and their indices as values. This allows us to quickly check if a character has already appeared within the current window.

### Sliding Window (start, end):

- `start`: Marks the beginning of the current window.
- `end`: Marks the current character we're processing.
- If a duplicate character is found (exists in charIndexMap and its index is greater than or equal to start), we update start to be one index after the duplicate character.

### Max Length:

The maximum length is updated after each iteration by calculating the length of the current window (end - start + 1).

## Example Walkthrough:

For the input "abcabcbb":

1. Iteration 1 (end = 0): Current character 'a'. No duplicate, start = 0. Max length = 1.
2. Iteration 2 (end = 1): Current character 'b'. No duplicate, start = 0. Max length = 2.
3. Iteration 3 (end = 2): Current character 'c'. No duplicate, start = 0. Max length = 3.
4. Iteration 4 (end = 3): Current character 'a'. Duplicate found at index 0, move start = 1. Max length = 3.
5. Iteration 5 (end = 4): Current character 'b'. Duplicate found at index 1, move start = 2. Max length = 3.
6. Iteration 6 (end = 5): Current character 'c'. Duplicate found at index 2, move start = 3. Max length = 3.
7. Iteration 7 (end = 6): Current character 'b'. No duplicate in window. Max length = 3.
8. Iteration 8 (end = 7): Current character 'b'. Duplicate found at index 6, move start = 7. Max length = 3.

Final result: The longest substring without repeating characters is "abc" with a length of 3.

### Input Example:
String s = "abcabcbb"

We will use a HashMap to track the index of each character and maintain a sliding window to find the longest substring without repeating characters.

### Initialization:
We initialize:
- charIndexMap: a HashMap to store the most recent index of each character.
- start = 0: the left boundary of the sliding window.
- maxLen = 0: to keep track of the length of the longest substring.

### Step-by-Step Iteration:

1. **Iteration 1: end = 0, character = 'a'**
   - The character 'a' is not in the charIndexMap.
   - Add 'a' to the map: {'a': 0}.
   - The window is from start = 0 to end = 0: 'a'.
   - Calculate the length of the current substring: end - start + 1 = 1.
   - Update maxLen: maxLen = 1.

2. **Iteration 2: end = 1, character = 'b'**
   - The character 'b' is not in the charIndexMap.
   - Add 'b' to the map: {'a': 0, 'b': 1}.
   - The window is from start = 0 to end = 1: 'ab'.
   - Calculate the length of the current substring: end - start + 1 = 2.
   - Update maxLen: maxLen = 2.

3. **Iteration 3: end = 2, character = 'c'**
   - The character 'c' is not in the charIndexMap.
   - Add 'c' to the map: {'a': 0, 'b': 1, 'c': 2}.
   - The window is from start = 0 to end = 2: 'abc'.
   - Calculate the length of the current substring: end - start + 1 = 3.
   - Update maxLen: maxLen = 3.

4. **Iteration 4: end = 3, character = 'a'**
   - The character 'a' is already in the charIndexMap, and its index (0) is within the current window (start = 0 to end = 3).
   - Action: Move the start pointer to max(start, charIndexMap.get('a') + 1) = max(0, 0 + 1) = 1.
   - Update the map with the new index of 'a': {'a': 3, 'b': 1, 'c': 2}.
   - The window is now from start = 1 to end = 3: 'bca'.
   - Calculate the length of the current substring: end - start + 1 = 3.
   - maxLen remains unchanged: maxLen = 3.

5. **Iteration 5: end = 4, character = 'b'**
   - The character 'b' is already in the charIndexMap, and its index (1) is within the current window (start = 1 to end = 4).
   - Action: Move the start pointer to max(start, charIndexMap.get('b') + 1) = max(1, 1 + 1) = 2.
   - Update the map with the new index of 'b': {'a': 3, 'b': 4, 'c': 2}.
   - The window is now from start = 2 to end = 4: 'cab'.
   - Calculate the length of the current substring: end - start + 1 = 3.
   - maxLen remains unchanged: maxLen = 3.

6. **Iteration 6: end = 5, character = 'c'**
   - The character 'c' is already in the charIndexMap, and its index (2) is within the current window (start = 2 to end = 5).
   - Action: Move the start pointer to max(start, charIndexMap.get('c') + 1) = max(2, 2 + 1) = 3.
   - Update the map with the new index of 'c': {'a': 3, 'b': 4, 'c': 5}.
   - The window is now from start = 3 to end = 5: 'abc'.
   - Calculate the length of the current substring: end - start + 1 = 3.
   - maxLen remains unchanged: maxLen = 3.

7. **Iteration 7: end = 6, character = 'b'**
   - The character 'b' is already in the charIndexMap, and its index (4) is within the current window (start = 3 to end = 6).
   - Action: Move the start pointer to max(start, charIndexMap.get('b') + 1) = max(3, 4 + 1) = 5.
   - Update the map with the new index of 'b': {'a': 3, 'b': 6, 'c': 5}.
   - The window is now from start = 5 to end = 6: 'cb'.
   - Calculate the length of the current substring: end - start + 1 = 2.
   - maxLen remains unchanged: maxLen = 3.

8. **Iteration 8: end = 7, character = 'b'**
   - The character 'b' is already in the charIndexMap, and its index (6) is within the current window (start = 5 to end = 7).
   - Action: Move the start pointer to max(start, charIndexMap.get('b') + 1) = max(5, 6 + 1) = 7.
   - Update the map with the new index of 'b': {'a': 3, 'b': 7, 'c': 5}.
   - The window is now from start = 7 to end = 7: 'b'.
   - Calculate the length of the current substring: end - start + 1 = 1.
   - maxLen remains unchanged: maxLen = 3.

### Final Output:
After going through all the characters, the maximum length of a substring without repeating characters is 3.
The substrings are 'abc', 'bca', or 'cab'.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string, as we traverse the string once and perform constant-time operations for each character.
- Space Complexity: O(min(m, n)), where m is the size of the character set, since we use a hash map to store characters and their indices, which can hold at most min(m, n) entries.
## LeetCode Link

[3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)