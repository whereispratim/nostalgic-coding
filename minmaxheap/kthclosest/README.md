# K Closest Points to Origin

## Problem Description

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
import heapq
from typing import List

class Solution:
def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
heap = []
for x, y in points:
dist = -(x*x + y*y)
if len(heap) == k:
heapq.heappushpop(heap, (dist, x, y))
else:
heapq.heappush(heap, (dist, x, y))

        return [[x, y] for (dist, x, y) in heap]

points = [[1,3],[-2,2]]
k = 1
solution = Solution()
result = solution.kClosest(points, k)
print(f"The {k} closest points to the origin are: {result}")
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.PriorityQueue;

public class KClosestPoints {
public int[][] kClosest(int[][] points, int k) {
// Create a max-heap (Priority Queue) to store points based on their distance to the origin
PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
(a, b) -> getDistance(b) - getDistance(a)  // Compare points based on their squared distance
);

        // Iterate through each point in the points array
        for (int[] point : points) {
            // Add the current point to the heap
            maxHeap.offer(point);
            
            // If the heap size exceeds k, remove the farthest point
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        // Prepare the result array to hold the k closest points
        int[][] result = new int[k][2];
        
        // Extract the points from the heap
        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }
        
        return result;
    }
    
    // Helper function to calculate squared distance from the origin
    private int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
    
    public static void main(String[] args) {
        KClosestPoints solution = new KClosestPoints();
        
        // Test case
        int[][] points = {{3,3}, {5,-1}, {-2,4}};
        int k = 2;
        
        // Get the k closest points
        int[][] result = solution.kClosest(points, k);
        
        // Print the result
        System.out.println("The " + k + " closest points to the origin are:");
        for (int[] point : result) {
            System.out.println("[" + point[0] + ", " + point[1] + "]");
        }
    }
}

</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a max heap (priority queue) to efficiently find the K closest points to the origin.

### How It Works (Step-by-Step):

Consider the points: [[1,3],[-2,2]] and k = 1

1. Initialize a max heap.

2. Iterate through the points:
   - For [1,3]: Calculate distance 1^2 + 3^2 = 10. Add to heap.
   - For [-2,2]: Calculate distance (-2)^2 + 2^2 = 8. Add to heap, then remove farthest point.

3. The heap now contains the K closest points.

4. Return these points as the result.

## Complexity Analysis

- Time Complexity: O(n log k), where n is the number of points. We iterate through all n points, and each heap operation takes O(log k) time.
- Space Complexity: O(k) to store the k closest points in the heap.

## LeetCode Link

[973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)