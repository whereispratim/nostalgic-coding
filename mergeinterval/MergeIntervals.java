import java.util.*;

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // Sort the intervals based on start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // If the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            // currentEnd = merged.getLast()[1];
            // nextStart = interval[0];
            // nextEnd = interval[1];
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) { // merged.getLast()[1]
                merged.add(interval);
            }
            // Otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                //currentEnd > nextStart
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        MergeIntervals solution = new MergeIntervals();
        int[][] result = solution.merge(intervals);
        System.out.println("Merged intervals: " + Arrays.deepToString(result));
    }
}
