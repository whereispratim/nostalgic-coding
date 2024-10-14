class NoOfPalindromeSubStr:
    def countSubstrings(self, s: str) -> int:
        count = 0

        # Helper function to expand around a center and count palindromic substrings
        def expandAroundCenter(left: int, right: int) -> int:
            cnt = 0
            # Expand outward while the characters at left and right are equal and within bounds
            while left >= 0 and right < len(s) and s[left] == s[right]:
                cnt += 1  # A new palindrome is found
                left -= 1  # Move left pointer outward
                right += 1  # Move right pointer outward
            return cnt

        # Iterate through each character and count palindromes centered at each position
        for i in range(len(s)):
            # Count odd-length palindromes centered at i
            count += expandAroundCenter(i, i)
            # Count even-length palindromes centered between i and i + 1
            count += expandAroundCenter(i, i + 1)

        return count

# Example usage
sol = NoOfPalindromeSubStr()
print(sol.countSubstrings("abc"))  # Output: 3 ("a", "b", "c")
print(sol.countSubstrings("aaa"))  # Output: 6 ("a", "a", "a", "aa", "aa", "aaa")
