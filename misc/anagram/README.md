# Making Anagrams

## Problem Description

Given two strings, a and b, that may or may not be of the same length, determine the minimum number of character deletions required to make a and b anagrams. Any characters can be deleted from either of the strings.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import defaultdict

class AnagramDeletions:

    @staticmethod
    def calculate_deletions_to_make_anagram(first_string, second_string):
        # Create a character count map
        character_count_map = defaultdict(int)

        # Count frequency of each character in the first string
        for character in first_string:
            character_count_map[character] += 1

        # Subtract frequency of each character in the second string
        for character in second_string:
            character_count_map[character] -= 1

        # Calculate the total number of deletions required
        total_deletions = sum(abs(count) for count in character_count_map.values())

        return total_deletions


if __name__ == "__main__":
first_string = "cde"
second_string = "abc"
result = AnagramDeletions.calculate_deletions_to_make_anagram(first_string, second_string)
print(f"Number of deletions required to make anagram: {result}")

</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.HashMap;
import java.util.Map;

public class AnagramDeletions {

    public static int calculateDeletionsToMakeAnagram(String firstString, String secondString) {
        Map<Character, Integer> characterCountMap = new HashMap<>();

        // Count frequency of each character in the first string
        for (char character : firstString.toCharArray()) {
            characterCountMap.put(character, characterCountMap.getOrDefault(character, 0) + 1);
        }

        // Subtract frequency of each character in the second string
        for (char character : secondString.toCharArray()) {
            characterCountMap.put(character, characterCountMap.getOrDefault(character, 0) - 1);
        }

        // Calculate the total number of deletions required
        int totalDeletions = 0;
        for (int count : characterCountMap.values()) {
            totalDeletions += Math.abs(count);
        }

        return totalDeletions;
    }

    public static void main(String[] args) {
        String firstString = "cde";
        String secondString = "abc";
        System.out.println("Number of deletions required to make anagram: " + calculateDeletionsToMakeAnagram(firstString, secondString));
    }
}


</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a frequency counting approach to determine the minimum number of character deletions required to make two strings anagrams.

### How It Works (Step-by-Step):

Consider the strings: str1 = "cde" and str2 = "abc"

1. Initialize a hash map to store character frequencies.

2. Count the frequency of characters in the first string (str1):
   - countMap = {'c': 1, 'd': 1, 'e': 1}

3. Subtract the frequency of characters in the second string (str2):
   - For 'a': countMap['a'] = -1
   - For 'b': countMap['b'] = -1
   - For 'c': countMap['c'] = 0 (1 - 1)

4. Calculate the total number of deletions:
   - Sum the absolute values of all counts in the map
   - |1| + |1| + |0| + |-1| + |-1| = 4

5. Return the total number of deletions (4)

### Key Concepts:

1. Single Hash Map: We use a single hash map to track the frequency difference between the two strings.

2. Frequency Counting and Subtraction: We increment counts for characters in the first string and decrement for the second string.

3. Absolute Sum: The sum of absolute values of the final counts gives the total number of deletions needed.

### Why This Works:

- Positive counts in the final map represent characters that need to be deleted from the first string.
- Negative counts represent characters that need to be deleted from the second string.
- Characters common to both strings in equal quantities will have a count of 0 and don't contribute to deletions.

## Complexity Analysis

- Time Complexity: O(n), where n is the total length of both strings. We iterate through each string once and then through the hash map.
- Space Complexity: O(1), as the space used by the hash map is constant (limited by the alphabet size, typically 26 for lowercase English letters).

## HackerRank Link

[Making Anagrams](https://www.hackerrank.com/challenges/ctci-making-anagrams/)