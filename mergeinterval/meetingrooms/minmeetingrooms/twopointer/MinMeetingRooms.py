class MinMeetingRooms:
    def __init__(self, intervals):
        self.intervals = intervals

    def minMeetingRooms(self):
        if not self.intervals:
            return 0

        # Step 1: Separate start and end times
        start_times = sorted([interval[0] for interval in self.intervals])
        end_times = sorted([interval[1] for interval in self.intervals])

        # Step 2: Use two pointers to count rooms
        start_pointer = 0
        end_pointer = 0
        rooms_required = 0
        max_rooms = 0

        while start_pointer < len(self.intervals):
            if start_times[start_pointer] < end_times[end_pointer]:
                # A new meeting is starting before the last one ends, need a new room
                rooms_required += 1
                start_pointer += 1
            else:
                # A meeting has ended, free up a room
                rooms_required -= 1
                end_pointer += 1

            # Track the maximum number of rooms required
            max_rooms = max(max_rooms, rooms_required)

        return max_rooms

# Example usage:
meeting_rooms_solver = MinMeetingRooms([[0, 30], [5, 10], [15, 20]])
print(meeting_rooms_solver.minMeetingRooms())  # Output: 2

meeting_rooms_solver_2 = MinMeetingRooms([[7, 10], [2, 4]])
print(meeting_rooms_solver_2.minMeetingRooms())  # Output: 1
