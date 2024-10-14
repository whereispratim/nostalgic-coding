class ProductExceptSelf:
    def productExceptSelf(self, nums):
        n = len(nums)
        answer = [1] * n  # Initialize result array with 1

        # Step 1: Compute the left products
        left_product = 1
        for i in range(n):
            answer[i] = left_product  # Set current answer to the left product
            left_product *= nums[i]  # Update left product for next iteration

        # Step 2: Compute the right products and update the result
        right_product = 1
        for i in range(n - 1, -1, -1):
            answer[i] *= right_product  # Multiply with the right product
            right_product *= nums[i]  # Update right product for next iteration

        return answer

# Sample usage
if __name__ == "__main__":
    solution = ProductExceptSelf()

    # Test case 1
    nums1 = [1, 2, 3, 4]
    print(solution.productExceptSelf(nums1))  # Output: [24, 12, 8, 6]

    # Test case 2
    nums2 = [-1, 1, 0, -3, 3]
    print(solution.productExceptSelf(nums2))  # Output: [0, 0, 9, 0, 0]
