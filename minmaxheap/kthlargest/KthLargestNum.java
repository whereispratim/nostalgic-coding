package kthlargest;

import java.util.PriorityQueue;
class KthLargestNum {
    public int findKthLargest(int[] nums, int k) {
        // Min-Heap to keep track of the k largest elements
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k); //OF SIZE K

        for (int num : nums) {
            minHeap.offer(num); // Add the current element to the heap

            // If the heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        KthLargestNum solution = new KthLargestNum();
        int result = solution.findKthLargest(nums, k);
        System.out.println("The " + k + "th largest element is: " + result);
    }
}
