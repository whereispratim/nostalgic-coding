package kthclosest;

import java.util.PriorityQueue;

class KClosestPoints {
    public int[][] kClosest(int[][] points, int k) {
        // Use a priority queue to keep track of the K closest points ()
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
        );

        // Add all points into the priority queue and keep size <= k
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > k) {
                pq.poll();  // Remove the farthest point if size exceeds k
            }
        }

        // Directly convert the priority queue into a result array
        return pq.toArray(new int[k][2]);
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

