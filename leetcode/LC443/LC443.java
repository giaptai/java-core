package LC443;

/*
Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
After you are done modifying the input array, return the new length of the array.
You must write an algorithm that uses only constant extra space.

Note: The characters in the array beyond the returned length do not matter and should be ignored.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: 6
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
After modifying the input array in-place, the first 6 characters of chars should be ["a","2","b","2","c","3"].

Example 2:
Input: chars = ["a"]
Output: 1
Explanation: The only group is "a", which remains uncompressed since it is a single character.
After modifying the input array in-place, the first character of chars should be ["a"].

Example 3:
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: 4
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".
After modifying the input array in-place, the first 4 characters of chars should be ["a","b","1","2"].
 
Constraints:
1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.

Pattern:
while (i < n) {

    j = i;

    while (j < n && condition) {
        j++;
    }

    // process group [i, j)

    i = j;
}
    
// two pointers reverse in-place:
left = start;
right = end;

while (left < right) {
    swap(left, right);
    left++;
    right--;
}
*/

public class LC443 {
    public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        while (j < chars.length) {
            if (chars[i] == chars[j]) {
                j++;
            }
            if (j == chars.length || chars[i] != chars[j]) {
                chars[k] = chars[i];
                k++;
                int count = j - i;
                if (count > 1) {
                    int start = k;
                    while (count > 0) {
                        chars[k++] = (char) ('0' + count % 10);
                        count /= 10;
                    }
                    for (int l = start, r = k - 1; l < r; l++, r--) {
                        char tmp = chars[l];
                        chars[l] = chars[r];
                        chars[r] = tmp;
                    }
                }
                i = j;
            }
        }
        return k;
    }

    public static int compress0(char[] chars) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < chars.length){
            if (i == j){
                j++;
                continue;
            }
            if (chars[i] == chars[j]){
                j++;
            } else {
                if (j == chars.length - 1){

                }
            }

        }
        return 1;
    }

    public static void main(String[] args) {
        // System.out.println(compress(new char[] { 'a', 'b', 'b', 'b' }));
        // System.out.println(compress(new char[] { 'a', 'a', 'b', 'b', 'b', 'c', 'c' }));
        System.out.println(compress(new char[] { 'a', 'b', 'b', 'b', 'b', 'b', 'b',
        'b', 'b', 'b', 'b', 'b', 'b' }));
    }
}
