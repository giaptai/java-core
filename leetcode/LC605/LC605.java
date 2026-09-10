package LC605;

/*
You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be planted in adjacent plots.
Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer n, 
return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule and false otherwise.


Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true

Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false

Constraints:
    1 <= flowerbed.length <= 2 * 104
    flowerbed[i] is 0 or 1.
    There are no two adjacent flowers in flowerbed.
    0 <= n <= flowerbed.length
*/
public class LC605 {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed.length == 1 && flowerbed[0] == 0) {
            return true;
        }
        for (int i = 0; i < flowerbed.length; i++) {
            if (n == 0) {
                break;
            }
            if ((i == 0 && flowerbed[i] == 0 && flowerbed[i + 1] == 0)
                    || (i == flowerbed.length - 1 && flowerbed[i] == 0 && flowerbed[i - 1] == 0)) {
                flowerbed[i] = 1;
                n--;
            }
            if (i != 0 && i != flowerbed.length - 1) {
                if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n == 0;
    }

    public static boolean canPlaceFlowers0(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            if (n == 0) {
                return true;
            }
            if (flowerbed[i] == 0) {
                // check left
                int left = (i == 0) ? 0 : flowerbed[i - 1];
                // check right
                int right = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
                if (left == 0 && right == 0){
                    flowerbed[i] = 1;
                    n--;
                }
            }
        }
        return n == 0;
    }

    public static void main(String[] args) {
        // System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        // System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
        // System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1,0,0}, 2));
        // System.out.println(canPlaceFlowers(new int[] { 0, 1, 0 }, 2));
        System.out.println(canPlaceFlowers(new int[] { 0, 0, 1, 0, 0 }, 1));
    }
}
