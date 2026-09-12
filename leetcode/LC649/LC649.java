package leetcode.LC649;

import java.util.Queue;
import java.util.LinkedList;

public class LC649{
    public static String predictPartyVictory(String senate) {
        Queue<Integer> r = new LinkedList<>();
        Queue<Integer> d = new LinkedList<>();

        for (int i = 0; i < senate.length(); i++){
            if (senate.charAt(i) == 'R'){
                r.offer(i);
            } else {
                d.offer(i);
            }
        }

        while (!r.isEmpty() && !d.isEmpty()){
            int p;
            if (r.peek() < d.peek()){
                p = r.poll();
                d.poll();
                r.offer(p + senate.length());
            } else {
                p = d.poll();
                r.poll();
                d.offer(p + senate.length());
            }
        }

        return r.isEmpty() ? "Dire" : "Radiant";
    }
    public static void main(String[] args) {
        System.out.println(predictPartyVictory("DDRRR"));
    }
}