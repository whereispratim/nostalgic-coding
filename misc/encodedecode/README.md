## Encode and Decode Strings

### Problem Statement

You are asked to implement two methods:

1. `encode`: Encodes a list of strings to a single string.
2. `decode`: Decodes a single string back to the original list of strings.

The encoding should be designed such that it can handle any string, including those containing special characters or delimiters.

### Examples

#### Example 1:

Input: `["Hello", "World"]`
Encoded: `"5#Hello5#World"`
Decoded: `["Hello", "World"]`

#### Example 2:

Input: `[""]`
Encoded: `"0#"`
Decoded: `[""]`

#### Example 3:

Input: `["Hello", "World", "Coding", "Is", "Fun"]`
Encoded: `"5#Hello5#World6#Coding2#Is3#Fun"`
Decoded: `["Hello", "World", "Coding", "Is", "Fun"]`

### Approach

We'll use a length-prefixed encoding scheme:
1. For encoding, we'll prefix each string with its length followed by a delimiter ('#' in this case).
2. For decoding, we'll read the length, then extract the string of that length.

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class Codec:
    def encode(self, strs):
        """Encodes a list of strings to a single string."""
        encoded_string = ""
        for s in strs:
            encoded_string += str(len(s)) + "#" + s
        return encoded_string

    def decode(self, s):
        """Decodes a single string to a list of strings."""
        decoded_strings = []
        i = 0

        while i < len(s):
            # Find the position of the next '#'
            j = s.index('#', i)
            length = int(s[i:j])  # Get the length of the string
            decoded_strings.append(s[j+1:j+1+length])  # Extract the string of that length
            i = j + 1 + length  # Move the pointer to the next encoded string

        return decoded_strings
codec = Codec()

# Test case 1
strings = ["Hello", "World"]
encoded = codec.encode(strings)
print(f"Encoded: {encoded}")  # Output: "5#Hello5#World"

decoded = codec.decode(encoded)
print(f"Decoded: {decoded}")  # Output: ["Hello", "World"]

# Test case 2
strings = ["foo", "bar", "baz"]
encoded = codec.encode(strings)
print(f"Encoded: {encoded}")  # Output: "3#foo3#bar3#baz"

decoded = codec.decode(encoded)
print(f"Decoded: {decoded}")  # Output: ["foo", "bar", "baz"]

</code></pre>
</td>
<td>
<pre><code class="java">

import java.util.*;

public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encoded = new StringBuilder();

        for (String s : strs) {
            encoded.append(s.length()).append("#").append(s);
        }

        return encoded.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> decoded = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            int j = s.indexOf('#', i);  // Find the position of the next '#'
            int length = Integer.parseInt(s.substring(i, j));  // Extract length
            decoded.add(s.substring(j + 1, j + 1 + length));  // Extract string
            i = j + 1 + length;  // Move to the next encoded string
        }

        return decoded;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

        // Test case 1
        List<String> strings = Arrays.asList("Hello", "World");
        String encoded = codec.encode(strings);
        System.out.println("Encoded: " + encoded);  // Output: "5#Hello5#World"

        List<String> decoded = codec.decode(encoded);
        System.out.println("Decoded: " + decoded);  // Output: ["Hello", "World"]

        // Test case 2
        strings = Arrays.asList("foo", "bar", "baz");
        encoded = codec.encode(strings);
        System.out.println("Encoded: " + encoded);  // Output: "3#foo3#bar3#baz"

        decoded = codec.decode(encoded);
        System.out.println("Decoded: " + decoded);  // Output: ["foo", "bar", "baz"]
    }
}
</code></pre>
</td>
</tr>
</table>

### Explanation

#### Encode:
1. For each string in the input list:
    - We prefix it with its length, followed by a '#' delimiter.
    - For example, "Hello" becomes "5#Hello".
2. We concatenate all these encoded strings.

#### Decode:
1. We iterate through the encoded string:
    - We find the next '#' delimiter to get the length of the next string.
    - We extract the string of that length.
    - We add the extracted string to our result list.
2. We continue this process until we've processed the entire encoded string.

### Detailed Walkthrough

Let's walk through the solution for `["Hello", "World"]`:

1. Encoding:
    - "Hello" becomes "5#Hello"
    - "World" becomes "5#World"
    - The final encoded string is "5#Hello5#World"

2. Decoding "5#Hello5#World":
    - Read "5", find "#", extract "Hello"
    - Move to next position, read "5", find "#", extract "World"
    - Result: ["Hello", "World"]

    
### Detailed Walkthrough of the Decode Method

1. **Initialization:**
    - We create an empty list `decoded` to store the decoded strings.
    - The variable `i` is initialized to 0, representing the current index in the encoded string.

2. **Decoding the encoded string:**
   The key steps inside the while loop are:

   a. **Find the length of the next string:**
    - We search for the position of the '#' character using `s.indexOf('#', i)`, which returns the position `j`.
    - The substring between `i` and `j` (`s.substring(i, j)`) represents the length of the next string.
    - Convert this substring to an integer (`Integer.parseInt()`).

   b. **Extract the string:**
    - Now that we know the length of the string, we can extract it from the encoded string.
    - The substring from `j + 1` to `j + 1 + length` is the actual string, which we add to the `decoded` list.

   c. **Move the index forward:**
    - After extracting the string, update `i` to the next position (`i = j + 1 + length`) for the next iteration.

### Example Walkthrough

Given the encoded string "5#Hello5#World":

1. **First Iteration:**
    - Starting at `i = 0`, we find the first '#' at position 1.
    - The substring "5" represents the length.
    - Extract the substring "Hello" from position 2 to 7.
    - Add "Hello" to the `decoded` list.
    - Update `i = 7` for the next iteration.

2. **Second Iteration:**
    - At `i = 7`, find the next '#' at position 8.
    - The substring "5" represents the length.
    - Extract the substring "World" from position 9 to 14.
    - Add "World" to the `decoded` list.

3. **Result:**
    - The `decoded` list now contains ["Hello", "World"]

This process efficiently decodes the string by using the length information to extract each original string without ambiguity, even if the strings contain special characters or delimiters.

### Complexity Analysis

- Time Complexity:
    - Encode: O(n), where n is the total length of all strings.
    - Decode: O(n), where n is the length of the encoded string.
- Space Complexity: O(n) for both encode and decode, to store the result.

### LeetCode Link

[271. Encode and Decode Strings](https://leetcode.com/problems/encode-and-decode-strings/)