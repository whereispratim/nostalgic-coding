package meetingrooms.minmeetingrooms.minheap;

import java.util.*;

class MinMeetingRooms {
    public int minMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }

        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        // Min heap to store end times of meetings
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.offer(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            if (meetings[i][0] >= rooms.peek()) {
                // If the current meeting starts after the
                // earliest ending meeting, we can reuse that room
                rooms.poll();
            }

            // Add the current meeting's end time to the heap
            rooms.offer(meetings[i][1]);
        }

        // The size of the heap is the minimum number of rooms required
        return rooms.size();
    }

    public static void main(String[] args) {
        MinMeetingRooms solution = new MinMeetingRooms();
        System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));  // 2
        System.out.println(solution.minMeetingRooms(new int[][]{{7,10},{2,4}}));  // 1
    }
}
