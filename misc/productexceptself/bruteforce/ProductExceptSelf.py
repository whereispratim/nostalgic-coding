class ProductExceptSelf:
    def productExceptSelf(self, nums):
        n = len(nums)
        result = [1] * n

        # For each element, compute the product of all other elements
        for i in range(n):
            product = 1
            for j in range(n):
                if i != j:
                    product *= nums[j]
            result[i] = product

        return result

# Example usage
solution = ProductExceptSelf()
nums = [1, 2, 3, 4]
print(solution.productExceptSelf(nums))  # Output: [24, 12, 8, 6]
