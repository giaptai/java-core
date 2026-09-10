/*
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 
Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 
Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
*/

package LC238;

class LC238 {
    public static int[] productExceptSelf(int[] nums) {
        int[] pr = new int[nums.length];
        // left
        int lr = 1;
        for (int i = 0; i < nums.length; i++) {
            pr[i] = lr;
            lr = lr * nums[i];
        }
        // right
        lr = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            pr[j] *= lr;
            lr = lr * nums[j];
        }
        return pr;
    }

    public static int[] productExceptSelf1(int[] nums) {
        int[] pl = new int[nums.length];
        int[] pr = new int[nums.length];
        // left
        int lr = 1;
        for (int i = 0; i < nums.length; i++) {
            pl[i] = lr;
            lr = lr * nums[i];
        }
        lr = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            pr[j] = lr;
            lr = lr * nums[j];
        }
        for (int k = 0; k < nums.length; k++) {
            nums[k] = pl[k] * pr[k];
        }
        return nums;
    }

    public static int[] productExceptSelf0(int[] nums) {
        int pl = 1;
        int i = 0;
        int[] result = new int[nums.length];
        for (i = 0; i < nums.length; i++) {
            result[i] = pl;
            pl *= nums[i];
        }
        pl = 1;
        // i = 0;
        for (i = nums.length - 1; i >= 0; i--) {
            result[i] *= pl;
            pl *= nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        productExceptSelf0(new int[] { 1, 2, 3, 4 });
        // productExceptSelf0(new int[] { -1, 1, 0, -3, 3 });
    }
}