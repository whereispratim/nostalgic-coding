from collections import Counter

class MinWindowSubStr:
    def minWindow(self, s: str, t: str) -> str:
        if not t or not s:
            return ""

        dict_t = Counter(t)
        required = len(dict_t)

        # Filter all the characters from s into a new list along with their index.
        filtered_s = [(i, char) for i, char in enumerate(s) if char in dict_t]

        left = 0
        formed = 0
        window_counts = {}

        ans = float("inf"), None, None

        # Look for the characters only in the filtered list instead of entire s
        for right, char in filtered_s:
            window_counts[char] = window_counts.get(char, 0) + 1

            if window_counts[char] == dict_t[char]:
                formed += 1

            # Try and contract the window till the point where it ceases to be 'desirable'.
            while left <= right and formed == required:
                char_left = filtered_s[left][1]

                # Save the smallest window until now.
                if right - filtered_s[left][0] + 1 < ans[0]:
                    ans = (right - filtered_s[left][0] + 1, filtered_s[left][0], right)

                window_counts[char_left] -= 1
                if window_counts[char_left] < dict_t[char_left]:
                    formed -= 1
                left += 1

        return "" if ans[0] == float("inf") else s[ans[1] : ans[2] + 1]

# Test the solution
solution = MinWindowSubStr()
print(solution.minWindow("ADOBECODEBANC", "ABC"))  # Output: "BANC"
print(solution.minWindow("a", "a"))  # Output: "a"
print(solution.minWindow("a", "aa"))  # Output: ""