"""
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
"""
class LC605:
    def canPlaceFlowers(self, flowerbed:list[int], n:int) -> bool:
        for i in range(len(flowerbed)):
            if n == 0:
                return True
            if flowerbed[i] == 0:
                # left
                left:int = 0 if i == 0 else flowerbed[i-1]
                # right
                right:int = 0 if i == len(flowerbed) -1 else flowerbed[i+1]
                if left == 0  and right == 0:
                    flowerbed[i] = 1
                    n-=1
        return n == 0
