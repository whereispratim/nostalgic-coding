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
    # Output: [['eat', 'tea', 'ate'], ['tan', 'nat'], ['bat']]
