class ContainerWithMostWater:
    def max_area(self, height):
        left = 0  # Left pointer
        right = len(height) - 1  # Right pointer
        max_water = 0  # To store the maximum water area

        while left < right:
            # Calculate the area between the current pair of lines
            width = right - left
            current_height = min(height[left], height[right])
            current_area = width * current_height

            # Update max_water if the current area is larger
            max_water = max(max_water, current_area)

            # Move the pointer corresponding to the shorter line inward
            if height[left] < height[right]:
                left += 1
            else:
                right -= 1

        return max_water  # Return the maximum water area

# Test cases
if __name__ == "__main__":
    solution = ContainerWithMostWater()

    # Test cases
    height1 = [1, 8, 6, 2, 5, 4, 8, 3, 7]
    height2 = [1, 1]

    print("Max Water Area (Test 1):", solution.max_area(height1))  # Output: 49
    print("Max Water Area (Test 2):", solution.max_area(height2))  # Output: 1
