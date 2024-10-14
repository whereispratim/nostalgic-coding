class LongestRepeatingSubstringReplacementK:
    def characterReplacement(self, s: str, k: int) -> int:
        char_count = {}
        max_count = 0
        max_length = 0
        start = 0

        for end in range(len(s)):
            char_count[s[end]] = char_count.get(s[end], 0) + 1
            max_count = max(max_count, char_count[s[end]])

            if end - start + 1 - max_count > k:
                char_count[s[start]] -= 1
                start += 1

            max_length = max(max_length, end - start + 1)

        return max_length

# Test the solution
solution = LongestRepeatingSubstringReplacementK()
print(solution.characterReplacement("ABAB", 2))    # Output: 4
print(solution.characterReplacement("AABABBA", 1)) # Output: 4