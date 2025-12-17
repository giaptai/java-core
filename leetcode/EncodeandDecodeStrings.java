package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Arrays;

public class EncodeandDecodeStrings {

    public static String encode(List<String> strs) {
        StringBuilder result = new StringBuilder();
        for (String s : strs) {
            int lenS = s.length();
            result.append(lenS).append("#").append(s);
        }
        return result.toString();
    }

    public static List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < str.length()) {
            int j = i;

            // get length of word
            while (str.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(str.substring(i, j));
            int start = j + 1;
            int end = start + len;
            String word = str.substring(start, end);

            result.add(word);
            i = end;
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> case1 = Arrays.asList("neet", "code", "love", "you");
        List<String> case2 = List.of("we", "say", ":", "yes");

        String s1 = encode(case1);
        String s2 = encode(case2);

        System.out.println(decode(s1));
        System.out.println(decode(s2));
    }

}
