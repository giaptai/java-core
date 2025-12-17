package leetcode;

import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Arrays;

public class TopKFrequent {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqNum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqNum.put(nums[i], freqNum.getOrDefault(nums[i], 0) + 1);
        }

        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(
                (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<Integer, Integer> item : freqNum.entrySet()) {
            queue.offer(item);
        }

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> top = queue.poll();
            result[i] = top.getKey();
        }

        return result;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 1, 1, 2, 2, 3 };
        int[] case2 = { 1, };
        int[] case3 = { 1, 2, 1, 2, 1, 2, 3, 1, 3, 2 };

        System.out.println(Arrays.toString(topKFrequent(case1, 2)));
        System.out.println(Arrays.toString(topKFrequent(case2, 1)));
        System.out.println(Arrays.toString(topKFrequent(case3, 2)));
    }
}
