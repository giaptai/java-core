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
function canPlaceFlowers(flowerbed, n) {
    if (flowerbed.length === 1 && flowerbed[0] === 0) {
        return true;
    }
    for (let i = 0; i < flowerbed.length; i++) {
        if (n === 0) {
            break;
        }
        if ((i === 0 && flowerbed[i] === 0 && flowerbed[i + 1] === 0) || (i === flowerbed.length - 1 && flowerbed[i] === 0 && flowerbed[i - 1] === 0)) {
            flowerbed[i] = 1;
            n--;
        }
        if (i !== 0 && i !== flowerbed.length - 1) {
            if (flowerbed[i] === 0 && flowerbed[i - 1] === 0 && flowerbed[i + 1] === 0) {
                flowerbed[i] = 1;
                n--;
            }
        }
    }
    return n === 0;
}


// System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
// System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
// System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1,0,0}, 2));
// System.out.println(canPlaceFlowers(new int[] { 0, 1, 0 }, 2));
// System.out.println(canPlaceFlowers(new int[] { 0, 0, 1, 0, 0 }, 1));
