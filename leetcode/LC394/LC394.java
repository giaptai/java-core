package leetcode.LC394;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC394 {
    public static String decodeString(String s) {
        String result = "";
        Deque<Integer> stNum = new ArrayDeque<>();
        Deque<String> stS = new ArrayDeque<>();
        Integer num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '[') {
                stNum.push(num);
                stS.push(result);
                num = 0;
                result = "";
            } else if (c == ']') {
                Integer repeat = stNum.pop();
                String preS = stS.pop();
                String temp = new String(preS);
                for (int j = 0; j < repeat; j++) {
                    temp += result;
                }
                result = temp;
            } else {
                result += c;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] s = new int[3];
        s.length
        System.out.println(decodeString("3[a]12[bc]"));
    }
}
