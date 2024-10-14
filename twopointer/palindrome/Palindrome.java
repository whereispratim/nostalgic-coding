package palindrome;

class Palindrome {
    public boolean isPalindrome(String s) {
        // Step 1: Clean up the string (convert to lowercase and remove non-alphanumeric characters)
        //OR    s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        StringBuilder cleanedStr = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                cleanedStr.append(Character.toLowerCase(c));
            }
        }

        // Step 2: Use two-pointer approach to check for palindrome
        int left = 0;
        int right = cleanedStr.length() - 1;
        while (left < right) {
            if (cleanedStr.charAt(left) != cleanedStr.charAt(right)) {
                return false; // Not a palindrome
            }
            left++;
            right--;
        }

        return true; // It's a palindrome
    }

    public static void main(String[] args) {
        Palindrome solution = new Palindrome();

        // Test case 1
        String s1 = "A man, a plan, a canal: Panama";
        boolean result1 = solution.isPalindrome(s1);
        System.out.println("Is \"" + s1 + "\" a palindrome? -> " + result1);  // Output: true

        // Test case 2
        String s2 = "race a car";
        boolean result2 = solution.isPalindrome(s2);
        System.out.println("Is \"" + s2 + "\" a palindrome? -> " + result2);  // Output: false
    }
}

