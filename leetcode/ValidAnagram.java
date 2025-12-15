package leetcode;

import java.util.Map;
import java.util.HashMap;

public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for (int i : map.values()) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    // solution 2
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] result = new int[26];

        for (int i = 0; i < s.length(); i++) {
            result[s.charAt(i) - 'a']++;
            result[t.charAt(i) - 'a']--;
        }

        for (int i : result) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s_case1 = "racecar", t_case1 = "carrace";
        System.out.println(isAnagram(s_case1, t_case1));
        String s_case2 = "jar", t_case2 = "jam";
        System.out.println(isAnagram(s_case2, t_case2));
    }
}
