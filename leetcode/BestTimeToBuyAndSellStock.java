package leetcode;

import java.lang.Math;

public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i : prices) {
            if (i < minPrice) {
                minPrice = i;
            } else {
                int profit = i - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] case1 ={7, 1, 5, 3, 6, 4};
        int[] case2 ={7, 6, 4, 3, 1};
        System.out.println(maxProfit(case1));
        System.out.println(maxProfit(case2));
    }
}
