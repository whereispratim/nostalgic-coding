## Container With Most Water

### Problem Statement

You are given an integer array `height` of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.

Notice that you may not slant the container.

### Examples

#### Example 1:

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

#### Example 2:

Input: height = [1,1]
Output: 1
# Container With Most Water

## Problem Statement

Given an array `height` of length `n`, where each element represents the height of a vertical line, find two lines that, together with the x-axis, form a container that holds the most water. The lines must be vertical, and you cannot slant the container.

## Approach

We use the **two-pointer technique** to solve this problem efficiently.

### Steps:
1. Start with one pointer at the beginning (`left = 0`) and another at the end (`right = n - 1`) of the `height` array.
2. Calculate the area between the lines at `left` and `right`.
3. Move the pointer corresponding to the shorter line inward. This is because moving the shorter line may increase the potential area.
4. Continue this process until the two pointers meet, while keeping track of the maximum area found.

### Formula for the Area:
The area of water between two lines is determined by:

- **Width** = `right - left` (the distance between the two lines).
- **Height** = `min(height[left], height[right])` (the shorter of the two lines).

Thus, the area is calculated as:

```css
Area = width * height = (right - left) * min(height[left], height[right])
```
### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
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

if __name__ == "__main__":
solution = ContainerWithMostWater()

    # Test cases
    height1 = [1, 8, 6, 2, 5, 4, 8, 3, 7]
    height2 = [1, 1]
    
    print("Max Water Area (Test 1):", solution.max_area(height1))  # Output: 49
    print("Max Water Area (Test 2):", solution.max_area(height2))  # Output: 1
</code></pre>
</td>
<td>
<pre><code class="java">
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;  // Left pointer
        int right = height.length - 1;  // Right pointer
        int maxWater = 0;  // Store the maximum water area found

        while (left < right) {
            // Calculate the area between the current pair of lines
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentArea = width * currentHeight;

            // Update the maximum water area if the current area is larger
            maxWater = Math.max(maxWater, currentArea);

            // Move the pointer corresponding to the shorter line
            if (height[left] < height[right]) {
                left++;  // Move the left pointer inward
            } else {
                right--;  // Move the right pointer inward
            }
        }

        return maxWater;  // Return the maximum area found
    }

    public static void main(String[] args) {
        ContainerWithMostWater solution = new ContainerWithMostWater();

        // Test cases
        int[] height1 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height2 = {1, 1};

        System.out.println("Max Water Area (Test 1): " + solution.maxArea(height1));  // Output: 49
        System.out.println("Max Water Area (Test 2): " + solution.maxArea(height2));  // Output: 1
    }
}
</code></pre>
</td>
</tr>
</table>

### Explanation

1. We start with two pointers, `left` at the beginning and `right` at the end of the array.
2. We calculate the area between these two lines: `width * min(height[left], height[right])`.
3. We update `max_area` if the current area is larger.
4. We move the pointer pointing to the shorter line inwards, because:
    - Moving the pointer of the taller line would only decrease the width without the possibility of increasing the height.
    - Moving the pointer of the shorter line gives us a chance to find a taller line, potentially increasing the area.
5. We repeat this process until the pointers meet.

### Detailed Walkthrough

Let's walk through the solution for height = [1,8,6,2,5,4,8,3,7]:

1. Initialize: `left = 0`, `right = 8`, `max_area = 0`
2. First iteration:
    - Width = 8 - 0 = 8
    - Height = min(1, 7) = 1
    - Area = 8 * 1 = 8
    - Update `max_area` to 8
    - Move `left` pointer (1 < 7)
3. Second iteration:
    - Width = 7
    - Height = min(8, 7) = 7
    - Area = 7 * 7 = 49
    - Update `max_area` to 49
    - Move `right` pointer (8 >= 7)
4. Continue this process...
5. Final result: 49

### Complexity Analysis

- Time Complexity: O(n), where n is the length of the height array. We make a single pass through the array.
- Space Complexity: O(1). We only use a constant amount of extra space.

### Key Observations

1. The two-pointer technique allows us to efficiently explore all possible containers.
2. By always moving the pointer pointing to the shorter line, we ensure that we don't miss the optimal solution.
3. This approach is much more efficient than the brute force method of checking all possible pairs of lines, which would be O(n^2).

### LeetCode Link

[11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)