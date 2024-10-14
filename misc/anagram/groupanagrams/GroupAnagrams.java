package anagram.groupanagrams;

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

            //OR
            /*if (!anagramGroups.containsKey(sortedWord)) {
                anagramGroups.put(sortedWord, new ArrayList<>());
            }
            anagramGroups.get(sortedWord).add(word);*/
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

