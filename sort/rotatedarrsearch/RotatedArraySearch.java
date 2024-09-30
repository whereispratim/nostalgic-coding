package rotatedarrsearch;

class RotatedArraySearch {
    public int searchInRotatedArray(int[] nums, int target) {
        int leftIndex = 0, rightIndex = nums.length - 1;

        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;

            // Check if the target is found at midIndex
            if (nums[midIndex] == target) {
                return midIndex;
            }

            // Check if the left half is sorted
            if (nums[leftIndex] <= nums[midIndex]) {
                // Target is in the sorted left half
                if (nums[leftIndex] <= target && target < nums[midIndex]) {
                    rightIndex = midIndex - 1; // Search in left half
                } else {
                    leftIndex = midIndex + 1; // Search in right half
                }
            }
            // The right half must be sorted
            else {
                // Target is in the sorted right half
                if (nums[midIndex] < target && target <= nums[rightIndex]) {
                    leftIndex = midIndex + 1; // Search in right half
                } else {
                    rightIndex = midIndex - 1; // Search in left half
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        RotatedArraySearch searcher = new RotatedArraySearch();
        System.out.println(searcher.searchInRotatedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));  // Output: 4
        System.out.println(searcher.searchInRotatedArray(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));  // Output: -1
    }
}
