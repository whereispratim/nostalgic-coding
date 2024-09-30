class RotatedArraySearch:
    def search_in_rotated_array(self, nums, target):
        left_index, right_index = 0, len(nums) - 1

        while left_index <= right_index:
            mid_index = left_index + (right_index - left_index) // 2

            # Check if the target is found at mid_index
            if nums[mid_index] == target:
                return mid_index

            # Check if the left half is sorted
            if nums[left_index] <= nums[mid_index]:
                # Target is in the sorted left half
                if nums[left_index] <= target < nums[mid_index]:
                    right_index = mid_index - 1  # Search in left half
                else:
                    left_index = mid_index + 1  # Search in right half
            # The right half must be sorted
            else:
                # Target is in the sorted right half
                if nums[mid_index] < target <= nums[right_index]:
                    left_index = mid_index + 1  # Search in right half
                else:
                    right_index = mid_index - 1  # Search in left half

        return -1  # Target not found

# Example usage
if __name__ == "__main__":
    searcher = RotatedArraySearch()
    print(searcher.search_in_rotated_array([4, 5, 6, 7, 0, 1, 2], 0))  # Output: 4
    print(searcher.search_in_rotated_array([4, 5, 6, 7, 0, 1, 2], 3))  # Output: -1
