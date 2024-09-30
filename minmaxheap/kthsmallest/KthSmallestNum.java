package kthsmallest;

import java.util.PriorityQueue;
import java.util.Collections;
class KthSmallestNum {
    public static int findKthSmallest(int[] nums, int k) {
        // Use a max-heap to keep track of the k smallest elements
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();  // Remove the largest element
            }
        }
        return maxHeap.peek();  // The root of the heap is the kth smallest element
    }

    public static void main(String[] args) {
        int[] nums = {7, 10, 4, 3, 20, 15};
        int k = 3;
        int result = findKthSmallest(nums, k);
        System.out.println("The " + k + "th smallest element is: " + result);
    }
}
