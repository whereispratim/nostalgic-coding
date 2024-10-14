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
