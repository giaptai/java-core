package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> listStr = new ArrayList<>();

        Map<String, List<String>> mapListStr = new HashMap<>();

        if (strs.length == 0) {
            return listStr;
        }
        for (String str : strs) {
            char[] tempChars = str.toCharArray();

            Arrays.sort(tempChars);

            String s = new String(tempChars);

            if (mapListStr.containsKey(s)) {
                mapListStr.get(s).add(str);
            } else {
                mapListStr.put(s, new ArrayList<String>(Arrays.asList(str)));
            }
        }
        listStr = new ArrayList<>(mapListStr.values());
        return listStr;
    }

    public static void main(String[] args) {
        String[] case1 = { "eat", "tea", "tan", "ate", "nat", "bat" };

        System.out.println(groupAnagrams(case1));
    }
}
