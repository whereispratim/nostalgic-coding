class Palindrome:
    def isPalindrome(self, s: str) -> bool:
        # Convert to lowercase and remove non-alphanumeric characters
        s = ''.join(c.lower() for c in s if c.isalnum())

        # Check if it's a palindrome
        left, right = 0, len(s) - 1
        while left < right:
            if s[left] != s[right]:
                return False
            left += 1
            right -= 1
        return True

# Test the solution
solution = Palindrome()
print(solution.isPalindrome("A man, a plan, a canal: Panama"))