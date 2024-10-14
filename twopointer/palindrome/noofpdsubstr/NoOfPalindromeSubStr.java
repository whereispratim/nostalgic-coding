package palindrome.noofpdsubstr;

public class NoOfPalindromeSubStr {
    public int countSubstrings(String s) {
        int count = 0;

        // Expand around each character (for odd-length palindromes)
        // and around each pair of adjacent characters (for even-length palindromes).
        for (int i = 0; i < s.length(); i++) {
            // Count odd-length palindromes centered at i
            count += expandAroundCenter(s, i, i);

            // Count even-length palindromes centered between i and i + 1
            count += expandAroundCenter(s, i, i + 1);
        }

        return count;
    }

    // Helper function to expand around a center and count palindromic substrings
    private int expandAroundCenter(String s, int left, int right) {
        int count = 0;

        // Expand outward while the characters at left and right are equal and within bounds
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;  // A new palindrome is found
            left--;   // Move left pointer outward
            right++;  // Move right pointer outward
        }

        return count;
    }

    public static void main(String[] args) {
        NoOfPalindromeSubStr sol = new NoOfPalindromeSubStr();

        // Test cases
        System.out.println(sol.countSubstrings("abc"));   // Output: 3 ("a", "b", "c")
        System.out.println(sol.countSubstrings("aaa"));   // Output: 6 ("a", "a", "a", "aa", "aa", "aaa")
    }
}

