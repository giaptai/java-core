package LC1679;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
You are given an integer array nums and an integer k.
In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
Return the maximum number of operations you can perform on the array.

Example 1:
Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.

Example 2:
Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 109
1 <= k <= 109
*/

public class LC1679 {
    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        int count = 0;
        while (i < j) {
            if (nums[i] + nums[j] == k) {
                i++;
                j--;
                count++;
            } else if (nums[i] + nums[j] < k) {
                i++;
            } else {
                j--;
            }
        }
        return count;
    }

    public static int maxOperations1(int[] nums, int k) {
        Map<Integer, Integer> m = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            int need = k - i;
            if(m.getOrDefault(need, 0) > 0){
                count++;
                m.put(need, m.get(need) - 1);
            }else {
                m.put(i, m.getOrDefault(i, 0) + 1);
            }
        }
        // put in here
        return count;
    }

    public static void main(String[] args) {
        System.out.println(maxOperations1(new int[] { 1, 2, 3, 4 }, 5));
        System.out.println(maxOperations1(new int[] { 3, 1, 3, 4, 3 }, 6));

    }
}
