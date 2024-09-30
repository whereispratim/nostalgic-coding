package anagram;

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

