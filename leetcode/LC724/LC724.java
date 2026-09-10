package leetcode.LC724;

public class LC724 {
    public static int pivotIndex0(int[] nums){
        int totalSum = 0;
        int totalLeft = 0;
        
        for (int i = 0; i < nums.length; i++){
            totalSum += nums[i];
        }

        for (int j = 0; j < nums.length; j++){
            int sumRight = totalSum - totalLeft - nums[j];
            if (sumRight == totalLeft){
                return j;
            }
            totalLeft += nums[j];
        }
        return -1;
    }
    public static int pivotIndex(int[] nums) {
        int[] num_l = new int[nums.length];
        int[] num_r = new int[nums.length];

        // calc left
        int n = 0;
        for (int i = 0; i < nums.length; i++) {
            n += nums[i];
            num_l[i] = n;
        }
        // calc right
        n = 0;
        for (int j = nums.length - 1; j >= 0; j--) {
            n += nums[j];
            num_r[j] = n;
        }

        for (int k = 0; k < nums.length; k++) {
            if (num_l[k] == num_r[k]) {
                return k;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(pivotIndex(new int[] { 1, 7, 3, 6, 5, 6 }));
    }
}
