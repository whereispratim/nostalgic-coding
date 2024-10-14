
import unittest
def isValid(s):
    """
    Returns True if the input string has valid brackets, False otherwise.

    :param s: Input string containing brackets.
    :return: Boolean indicating validity of brackets.
    """
    stack = []
    bracket_map = {')': '(', '}': '{', ']': '['}

    for char in s:
        if char in bracket_map.values():
            stack.append(char)
        elif char in bracket_map.keys():
            if not stack or stack.pop() != bracket_map[char]:
                return False

    return not stack


# Example usage
print(isValid("()"))  # True
print(isValid("()[]{}"))  # True
print(isValid("(]"))  # False
print(isValid("([])"))  # True


class TestIsValid(unittest.TestCase):
    def test_valid(self):
        self.assertTrue(isValid("()"))
        self.assertTrue(isValid("()[]{}"))
        self.assertTrue(isValid("([])"))

    def test_invalid(self):
        self.assertFalse(isValid("(]"))
        self.assertFalse(isValid("([)]"))
        self.assertFalse(isValid("("))


if __name__ == "__main__":
    unittest.main()