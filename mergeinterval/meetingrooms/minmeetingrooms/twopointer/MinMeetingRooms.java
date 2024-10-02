package meetingrooms.minmeetingrooms.twopointer;

import java.util.Arrays;

class MinMeetingRooms {
    public int minMeetingRooms(int[][] meetings) {
        int totalMeetings = meetings.length;
        // Step 1: Extract start and end times of meetings
        int[] startTimes = new int[totalMeetings];
        int[] endTimes = new int[totalMeetings];

        for (int i = 0; i < totalMeetings; i++) {
            startTimes[i] = meetings[i][0];
            endTimes[i] = meetings[i][1];
        }

        // Step 2: Sort both start and end times
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        // Step 3: Initialize two pointers for start and end times
        int currentRooms = 0;
        int maxRoomsNeeded = 0;
        int startPointer = 0;
        int endPointer = 0;

        // Step 4: Traverse through all meetings using startPointer
        while (startPointer < totalMeetings) {
            // If there's a meeting that has started before the previous one ends, allocate a new room
            if (startTimes[startPointer] < endTimes[endPointer]) {
                currentRooms++;
                startPointer++;
            } else {
                // Otherwise, a meeting has ended and we can free a room
                currentRooms--;
                endPointer++;
            }

            // Keep track of the maximum number of rooms required at any point
            maxRoomsNeeded = Math.max(maxRoomsNeeded, currentRooms);
        }

        return maxRoomsNeeded;
    }

    public static void main(String[] args) {
        MinMeetingRooms solution = new MinMeetingRooms();
        System.out.println(solution.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));  // 2
        System.out.println(solution.minMeetingRooms(new int[][]{{7,10},{2,4}}));  // 1
    }
}
