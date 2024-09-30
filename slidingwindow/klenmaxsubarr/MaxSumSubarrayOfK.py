class MaxSumSubarrayOfK:
    def __init__(self, array):
        self.array = array

    def max_sum_of_subarray(self, subarray_size):
        # Edge case: if the array length is less than the size of the subarray
        if len(self.array) < subarray_size:
            return -1  # or raise an exception

        current_window_sum = sum(self.array[:subarray_size])  # Calculate the sum of the first 'subarray_size' elements
        max_sum = current_window_sum

        # Iterate over the array starting from the 'subarray_size' index
        for i in range(subarray_size, len(self.array)):
            # Update the window sum: remove the element going out of the window
            # and add the new element coming into the window
            current_window_sum = current_window_sum - self.array[i - subarray_size] + self.array[i]
            max_sum = max(max_sum, current_window_sum)  # Update the maximum sum

        return max_sum  # Return the maximum sum of the subarray found

# Example usage
if __name__ == "__main__":
    array = [1, 4, 2, 10, 23, 3, 1, 0, 20]
    subarray_size = 4
    calculator = MaxSumSubarrayOfK(array)
    result = calculator.max_sum_of_subarray(subarray_size)
    print(f"Maximum sum of subarray of size {subarray_size}: {result}")
