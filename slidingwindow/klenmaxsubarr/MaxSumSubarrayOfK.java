package klenmaxsubarr;

public class MaxSumSubarrayOfK {

    public static int maxSumOfSubarray(int[] array, int subarraySize) {
        // Edge case: if the array length is less than the size of the subarray
        if (array.length < subarraySize) {
            return -1; // or throw an exception
        }

        int currentWindowSum = 0;

        // Calculate the sum of the first 'subarraySize' elements
        for (int i = 0; i < subarraySize; i++) {
            currentWindowSum += array[i];
        }

        int maxSum = currentWindowSum;

        // Iterate over the array starting from the 'subarraySize' index
        for (int i = subarraySize; i < array.length; i++) {
            // Update the window sum: remove the element going out of the window
            // and add the new element coming into the window
            currentWindowSum = currentWindowSum - array[i - subarraySize] + array[i];
            maxSum = Math.max(maxSum, currentWindowSum); // Update the maximum sum
        }

        return maxSum; // Return the maximum sum of the subarray found
    }

    public static void main(String[] args) {
        int[] array = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int subarraySize = 4;
        int result = maxSumOfSubarray(array, subarraySize);
        System.out.println("Maximum sum of subarray of size " + subarraySize + ": " + result);
    }
}

