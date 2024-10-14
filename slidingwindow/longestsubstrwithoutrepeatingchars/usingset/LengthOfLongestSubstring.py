class LengthOfLongestSubstring:
    def lengthOfLongestSubstring(self, s: str) -> int:
        char_set = set()
        max_length = 0
        start = 0

        for end in range(len(s)):
            while s[end] in char_set:
                char_set.remove(s[start])
                start += 1
            char_set.add(s[end])
            max_length = max(max_length, end - start + 1)

        return max_length

# Test the solution
solution = LengthOfLongestSubstring()
print(solution.lengthOfLongestSubstring("abcabcbb"))  # Output: 3
print(solution.lengthOfLongestSubstring("bbbbb"))     # Output: 1
print(solution.lengthOfLongestSubstring("pwwkew"))    # Output: 3