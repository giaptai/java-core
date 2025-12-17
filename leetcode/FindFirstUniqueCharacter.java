package leetcode;

import java.util.Map;
import java.util.HashMap;

public class FindFirstUniqueCharacter {
    public static int firstUniqChar(String s) {
        Map<Character, Integer> freqChar = new HashMap<>();
        // count frequency of each element
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            freqChar.put(c, freqChar.getOrDefault(c, 0) + 1);
        }
        
        // check first element apparence only one
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if(freqChar.get(c) == 1){
                return j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String case1 = "leetcode";
        String case2 = "loveleetcode";
        String case3 = "aabb";
        System.out.println(firstUniqChar(case1));
        System.out.println(firstUniqChar(case2));
        System.out.println(firstUniqChar(case3));
    }
}
