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


# Example usage
if __name__ == "__main__":
    solution = LengthOfLongestSubstring()
    input_string = "abcabcbb"
    result = solution.length_of_longest_substring(input_string)
    print(f"Length of the longest substring without repeating characters: {result}")
