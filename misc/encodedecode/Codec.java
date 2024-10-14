package encodedecode;

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
