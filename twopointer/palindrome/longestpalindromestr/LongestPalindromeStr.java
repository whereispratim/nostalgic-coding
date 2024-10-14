package palindrome.longestpalindromestr;

public class LongestPalindromeStr {

    // Helper function to expand around the center and return the longest palindrome found
    private String expandAroundCenter(String s, int left, int right) {
        // Expand outward while the characters on both sides are equal
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Return the palindromic substring found
        return s.substring(left + 1, right);
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        String longest = "";

        // Loop through each character in the string as a potential center
        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindrome (single character center)
            String oddPalindrome = expandAroundCenter(s, i, i);
            if (oddPalindrome.length() > longest.length()) {
                longest = oddPalindrome;
            }

            // Even-length palindrome (two-character center)
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > longest.length()) {
                longest = evenPalindrome;
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        LongestPalindromeStr solution = new LongestPalindromeStr();

        // Test cases
        System.out.println(solution.longestPalindrome("babad"));  // Output: "bab" or "aba"
        System.out.println(solution.longestPalindrome("cbbd"));   // Output: "bb"
    }
}

