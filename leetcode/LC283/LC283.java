package LC283;

/*
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]
 
Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1

Follow up: Could you minimize the total number of operations done?
*/
public class LC283 {
    public static void moveZeroes(int[] nums) {
        int pos = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[pos] = n;
                pos++;
            }
        }
        while (pos < nums.length) {
            nums[pos++] = 0;
        }
    }

    public static void moveZeroes1(int[] nums) {
        int i = 0;
        int j = 1;
        while (i < nums.length && j < nums.length) {
            // get 0 value
            while (i < nums.length && nums[i] != 0) {
                i++;
            }
            // j always behinds i
            if (j <= i) {
                j = i + 1;
            }
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
            if (i < nums.length && j < nums.length) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        moveZeroes(new int[] { 0, 1, 0, 3, 12 });
        // moveZeroes(new int[] { 0 });
        // moveZeroes(new int[] { 0, 0 });
        // moveZeroes(new int[] { 0, 1 });
    }
}
