package topkfrequent.bigon;

import java.util.*;

class TopKFrequentNumbersByBucketing {

    public List<Integer> findTopKFrequentNumbers(int[] numbers, int k) {
        // Step 1: Count the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        // Step 2: Create an array of lists (buckets) to group numbers by frequency
        List<Integer>[] frequencyBuckets = new ArrayList[numbers.length + 1];
        for (int i = 0; i <= numbers.length; i++) {
            frequencyBuckets[i] = new ArrayList<>();
        }

        // Step 3: Place each number into its corresponding frequency bucket
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            int number = entry.getKey();
            int frequency = entry.getValue();
            frequencyBuckets[frequency].add(number);
        }

        // Step 4: Collect the top 'K' frequent numbers from the buckets, starting from the highest frequency
        List<Integer> topKFrequentNumbers = new ArrayList<>();
        for (int i = frequencyBuckets.length - 1; i > 0; i--) {  // Start from the highest frequency bucket
            for (int number : frequencyBuckets[i]) {
                topKFrequentNumbers.add(number);
                if (topKFrequentNumbers.size() == k) {  // Stop when we have collected 'K' numbers
                    return topKFrequentNumbers;
                }
            }
        }

        return topKFrequentNumbers;  // This should not be reached if input is valid
    }

    public static void main(String[] args) {
        TopKFrequentNumbersByBucketing finder = new TopKFrequentNumbersByBucketing();

        // Test case 1
        int[] numbers1 = {1, 3, 5, 12, 11, 12, 11};
        int k1 = 2;
        List<Integer> result1 = finder.findTopKFrequentNumbers(numbers1, k1);
        System.out.println("Top K frequent numbers: " + result1);  // Expected output: [12, 11]

        // Test case 2
        int[] numbers2 = {5, 12, 11, 3, 11};
        int k2 = 2;
        List<Integer> result2 = finder.findTopKFrequentNumbers(numbers2, k2);
        System.out.println("Top K frequent numbers: " + result2);  // Expected output: [11, 5] or [11, 12] or [11, 3]
    }
}

