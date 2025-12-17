package leetcode;

import java.util.Arrays;

public class MoveZeroes {

    public static int[] moveZeroes(int[] nums) {
        int idx = 0;                            // remain position of element has zero value
        for (int i = 0; i < nums.length; i++) { // iterate through a array to swap value of element different zero value
            if (nums[i] != 0) {
                nums[idx] = nums[i];
                idx++;
            }
        }

        while (idx < nums.length) {             // iterate from idx to end of array
            nums[idx] = 0;                      // set zero value for remain elements
            idx++;                              // increase idx value
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] case1 = { 0, 1, 0, 3, 12 };
        System.out.println(Arrays.toString(moveZeroes(case1)));
    }

}