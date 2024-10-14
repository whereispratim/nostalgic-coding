package medianofstream;
import java.util.PriorityQueue;

class MedianOfStream {
    private PriorityQueue<Integer> lowerHalfMaxHeap;  // Max heap to store the smaller half of the numbers
    private PriorityQueue<Integer> upperHalfMinHeap;  // Min heap to store the larger half of the numbers

    public MedianOfStream() {
        lowerHalfMaxHeap = new PriorityQueue<>((a, b) -> b - a);  // Max heap to store the lower half
        upperHalfMinHeap = new PriorityQueue<>();  // Min heap to store the upper half
    }

    // Method to insert a number into the stream
    public void insertNumber(int number) {
        // Balance the heaps by alternating insertion between them
        if (lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            lowerHalfMaxHeap.offer(number);
            upperHalfMinHeap.offer(lowerHalfMaxHeap.poll());
        } else {
            upperHalfMinHeap.offer(number);
            lowerHalfMaxHeap.offer(upperHalfMinHeap.poll());
        }
    }

    // Method to find the median of the numbers inserted so far
    public double findMedian() {
        // If both heaps are the same size, the median is the average of the two middle elements
        if (lowerHalfMaxHeap.size() == upperHalfMinHeap.size()) {
            return (lowerHalfMaxHeap.peek() + upperHalfMinHeap.peek()) / 2.0;
        } else {
            // Otherwise, the median is the top element of the heap with more elements (upperHalfMinHeap)
            return upperHalfMinHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianOfStream medianCalculator = new MedianOfStream();
        medianCalculator.insertNumber(3);
        medianCalculator.insertNumber(1);
        System.out.println(medianCalculator.findMedian());  // Output: 2.0
        medianCalculator.insertNumber(5);
        System.out.println(medianCalculator.findMedian());  // Output: 3.0
        medianCalculator.insertNumber(4);
        System.out.println(medianCalculator.findMedian());  // Output: 3.5
    }
}
