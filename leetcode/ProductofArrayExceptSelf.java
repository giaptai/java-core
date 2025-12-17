package leetcode;

public class ProductofArrayExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int j = nums.length - 1; j >= 0; j--) {
            result[j] *= suffix;
            suffix *= nums[j];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] case1 = { 1, 2, 3, 4 };
        int[] case2 = { -1, 1, 0, -3, 3 };
        System.out.println(productExceptSelf(case1));
        System.out.println(productExceptSelf(case2));
    }
}
