# Group Anagrams

## Problem Description

Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

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

class GroupAnagrams:
def group_anagrams(self, words):
anagram_groups = defaultdict(list)

        for word in words:
            # Sort the characters of the word to use as a key
            sorted_word = ''.join(sorted(word))
            # Append the original word to the corresponding anagram group
            anagram_groups[sorted_word].append(word)

        return list(anagram_groups.values())

if __name__ == "__main__":
anagram_grouper = GroupAnagrams()
input_words = ["eat", "tea", "tan", "ate", "nat", "bat"]
result = anagram_grouper.group_anagrams(input_words)
print(result)
</code></pre>
</td>
<td>
<pre><code class="java">

import java.util.*;

class GroupAnagrams {
public List<List<String>> groupAnagrams(String[] words) {
Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String word : words) {
            char[] characters = word.toCharArray();
            Arrays.sort(characters);
            String sortedWord = new String(characters);

            // If the sorted string is not in the map, add it
            // Add the original string to the list corresponding to the sorted key
            anagramGroups.computeIfAbsent(sortedWord, k -> new ArrayList<>()).add(word);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    public static void main(String[] args) {
        GroupAnagrams anagramGrouper = new GroupAnagrams();
        String[] inputWords = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = anagramGrouper.groupAnagrams(inputWords);
        System.out.println(result);
        // Output: [[bat], [nat, tan], [ate, eat, tea]]
    }
}


</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a hash map to group anagrams together based on their sorted character representation.

### How It Works (Step-by-Step):

Let's consider the example: strs = ["eat","tea","tan","ate","nat","bat"]

1. Initialize an empty hash map (anagram_groups)

2. Iterate through each string in strs:
   - For "eat":
      * Sort the characters: "aet"
      * Add to hash map: {"aet": ["eat"]}

   - For "tea":
      * Sort the characters: "aet"
      * Add to existing group: {"aet": ["eat", "tea"]}

   - For "tan":
      * Sort the characters: "ant"
      * Create new group: {"aet": ["eat", "tea"], "ant": ["tan"]}

   - For "ate":
      * Sort the characters: "aet"
      * Add to existing group: {"aet": ["eat", "tea", "ate"], "ant": ["tan"]}

   - For "nat":
      * Sort the characters: "ant"
      * Add to existing group: {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"]}

   - For "bat":
      * Sort the characters: "abt"
      * Create new group: {"aet": ["eat", "tea", "ate"], "ant": ["tan", "nat"], "abt": ["bat"]}

3. Return the values of the hash map as a list:
   [["eat", "tea", "ate"], ["tan", "nat"], ["bat"]]

### Key Concepts:

1. Sorting Characters: We sort the characters of each string to create a unique key for anagrams.
2. Hash Map: We use a hash map to group strings with the same sorted character representation.
3. Defaultdict/computeIfAbsent: These methods allow us to easily create new lists for new sorted strings.

### Why This Works:

- Anagrams will have the same sorted character representation.
- By using this sorted representation as a key in our hash map, we can group anagrams together efficiently.

## Complexity Analysis

- Time Complexity: O(n * k log k), where n is the number of strings and k is the maximum length of a string. We iterate through all strings (n) and sort each string (k log k).
- Space Complexity: O(n * k), as we store all strings in our hash map.

## LeetCode Link

[49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)