class MaxSumDistnctSubarrayOfK:
    def maximum_distinct_subarray_sum(self, nums, k):
        distinct_elements = set()
        current_window_sum = 0
        max_distinct_sum = 0

        for i in range(len(nums)):
            # Maintain the size of the window and ensure all elements are distinct
            while len(distinct_elements) == k or nums[i] in distinct_elements:
                current_window_sum -= nums[i - len(distinct_elements)]
                distinct_elements.remove(nums[i - len(distinct_elements)])

            # Add the current element to the window
            distinct_elements.add(nums[i])
            current_window_sum += nums[i]

            # Update the maximum sum if the window size is k
            if len(distinct_elements) == k:
                max_distinct_sum = max(max_distinct_sum, current_window_sum)

        return max_distinct_sum  # Return the maximum sum of the distinct subarray


if __name__ == "__main__":
    nums = [1, 5, 4, 2, 9, 9, 9]
    k = 3
    calculator = MaxSumDistnctSubarrayOfK()
    result = calculator.maximum_distinct_subarray_sum(nums, k)
    print(f"Maximum sum of distinct subarray of length {k}: {result}")
