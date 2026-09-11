
package leetcode.LC1657;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;

public class LC1657 {
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();

        for (int i = 0; i < word1.length(); i++) {
            m1.put(word1.charAt(i), m1.getOrDefault(word1.charAt(i), 0) + 1);
            m2.put(word2.charAt(i), m2.getOrDefault(word2.charAt(i), 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            if (!m2.containsKey(entry.getKey())) {
                return false;
            }
        }

        List<Integer> l1 = new ArrayList<>(m1.values());
        List<Integer> l2 = new ArrayList<>(m2.values());
        Collections.sort(l1);
        Collections.sort(l2);
        if (!l1.equals(l2)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(closeStrings("cabbba", "abbccc"));

    }
}
