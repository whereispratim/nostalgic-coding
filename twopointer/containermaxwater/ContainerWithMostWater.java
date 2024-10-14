package containermaxwater;

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

