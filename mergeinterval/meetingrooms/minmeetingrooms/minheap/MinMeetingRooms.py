import heapq

class MinMeetingRooms:
    def min_meeting_rooms(self, meeting_intervals):
        if not meeting_intervals:
            return 0

        # Step 1: Sort the meetings by start time
        meeting_intervals.sort(key=lambda x: x[0])

        # Step 2: Initialize a min-heap to track end times of meetings
        meeting_rooms = []

        # Add the first meeting's end time to the heap
        heapq.heappush(meeting_rooms, meeting_intervals[0][1])

        # Step 3: Iterate over the remaining meetings
        for i in range(1, len(meeting_intervals)):
            # If the next meeting starts after the earliest end time, reuse the room
            if meeting_intervals[i][0] >= meeting_rooms[0]:
                heapq.heappop(meeting_rooms)  # Remove the room that got freed

            # Push the current meeting's end time into the heap
            heapq.heappush(meeting_rooms, meeting_intervals[i][1])

        # Step 4: The size of the heap is the number of rooms required
        return len(meeting_rooms)


# Example Walkthrough:
scheduler = MinMeetingRooms()
# Example 1
meetings = [[0, 30], [5, 10], [15, 20]]
print("Minimum rooms required:", scheduler.min_meeting_rooms(meetings))  # Output: 2

# Example 2
meetings = [[7, 10], [2, 4]]
print("Minimum rooms required:", scheduler.min_meeting_rooms(meetings))  # Output: 1
