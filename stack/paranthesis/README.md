# Valid Parentheses

## Problem Statement

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.

## Examples

### Example 1:

Input: s = "()"
Output: true

### Example 2:

Input: s = "()[]{}"
Output: true

### Example 3:

Input: s = "(]"
Output: false

### Example 4:

Input: s = "([)]"
Output: false

### Example 5:

Input: s = "{[]}"
Output: true

## Approach

1. Use a stack to keep track of opening brackets.
2. When encountering an opening bracket, push it onto the stack.
3. When encountering a closing bracket, check if the stack is empty or if the top of the stack doesn't match the corresponding opening bracket. If either condition is true, return false.
4. If the closing bracket matches the top of the stack, pop the top element from the stack.
5. After processing all characters, check if the stack is empty. If it is, return true; otherwise, return false.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        bracket_map = {")": "(", "}": "{", "]": "["}

        for char in s:
            if char in bracket_map:
                if not stack or stack[-1] != bracket_map[char]:
                    return False
                stack.pop()
            else:
                stack.append(char)
        
        return len(stack) == 0

solution = Solution()
print(solution.isValid("()"))       # Output: True
print(solution.isValid("()[]{}"))   # Output: True
print(solution.isValid("(]"))       # Output: False
print(solution.isValid("([)]"))     # Output: False
print(solution.isValid("{[]}"))     # Output: True
</code></pre>
</td>
<td>
<pre><code class="java">

import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class ValidParanthesis {
public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        Map<Character, Character> bracketMap = new HashMap<>();

        bracketMap.put(')', '(');
        bracketMap.put(']', '[');
        bracketMap.put('}', '{');

        for (char c : s.toCharArray()) {
            if (bracketMap.containsValue(c)) {
                stack.push(c);
            } else if (bracketMap.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != bracketMap.get(c)) { // popped to make stack empty
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParanthesis validator = new ValidParanthesis();
        System.out.println(validator.isValid("()"));  // true
        System.out.println(validator.isValid("()[]{}"));  // true
        System.out.println(validator.isValid("(]"));  // false
        System.out.println(validator.isValid("([])"));  // true
    }
}
</code></pre>
</td>
</tr>
</table>

## Explanation

## Detailed Walkthrough

Let's walk through each iteration of the `isValid` function for different input strings:

### Example 1: s = "([])":

1. **Iteration 1**
    - char = '('
    - stack = []
    - bracket_map = {')': '(', '}': '{', ']': '['}
    - Since char is an opening bracket '(', push it onto the stack: stack = ['(']

2. **Iteration 2**
    - char = '['
    - stack = ['(']
    - Push char onto the stack: stack = ['(', '[']

3. **Iteration 3**
    - char = ']'
    - stack = ['(', '[']
    - char is a closing bracket ']' and matches the top of the stack '[':
    - Pop '[' from the stack: stack = ['(']
    - Continue to the next iteration

4. **Iteration 4**
    - char = ')'
    - stack = ['(']
    - char is a closing bracket ')' and matches the top of the stack '(':
    - Pop '(' from the stack: stack = []
    - Continue to the next iteration

5. **Iteration 5**
    - No more characters in the string
    - stack = [] (empty stack)
    - Return True because the stack is empty, indicating valid brackets

### Example 2: s = "()[]{}":

1. '(' -> stack = ['(']
2. ')' -> pop '('; stack = []
3. '[' -> stack = ['[']
4. ']' -> pop '['; stack = []
5. '{' -> stack = ['{']
6. '}' -> pop '{'; stack = []
7. Return True (stack is empty)

### Example 3: s = "(]":

1. '(' -> stack = ['(']
2. ']' -> mismatched bracket; return False

### Example 4: s = "(":

1. '(' -> stack = ['(']
2. No more characters; return False because stack is not empty

## Key Points:

1. The stack is used to keep track of opening brackets.
2. When a closing bracket is encountered, it must match the most recent opening bracket (top of the stack).
3. If there's a mismatch or the stack is empty when encountering a closing bracket, the string is invalid.
4. After processing all characters, the stack must be empty for the string to be valid.

## Complexity Analysis

- Time Complexity: O(n), where n is the length of the input string. We iterate through the string once.
- Space Complexity: O(n) in the worst case, where all opening brackets are stored in the stack.

## LeetCode Link

[20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)