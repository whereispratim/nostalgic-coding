package paranthesis;

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