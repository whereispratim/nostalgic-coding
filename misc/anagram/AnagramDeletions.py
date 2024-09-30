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
