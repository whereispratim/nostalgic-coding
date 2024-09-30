import heapq
from typing import List

class KthSmallestNum:
    def findKthSmallest(self, nums: List[int], k: int) -> int:
        max_heap = []

        for num in nums:
            if len(max_heap) < k:
                heapq.heappush(max_heap, -num)  # Use negative for max-heap
            elif -num > max_heap[0]:
                heapq.heapreplace(max_heap, -num)

        return -max_heap[0]  # The root of the heap is the kth smallest element

# Example usage
if __name__ == "__main__":
    nums = [7, 10, 4, 3, 20, 15]
    k = 3
    solution = KthSmallestNum()
    result = solution.findKthSmallest(nums, k)
    print(f"The {k}th smallest element is: {result}")
