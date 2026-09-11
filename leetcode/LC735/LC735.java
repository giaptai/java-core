package leetcode.LC735;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC735 {
    public static int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> de = new ArrayDeque<>();

        for (int i = 0; i < asteroids.length; i++) {
            // handle collision
            while (!de.isEmpty() && de.peek() > 0 && asteroids[i] < 0) {
                if (Math.abs(asteroids[i]) > Math.abs(de.peek())) {
                    de.pop();
                } else if (Math.abs(asteroids[i]) < Math.abs(de.peek())) {
                    asteroids[i] = 0; // set current is destroyed
                } else {
                    de.pop();
                    asteroids[i] = 0; // set current is destroyed
                }
            }
            // current asterioid is dead
            if (asteroids[i] == 0) {
                continue;
            }
            // current asterioid alive
            if (asteroids[i] != 0) {
                de.push(asteroids[i]);
            }

        }
        int[] result = new int[de.size()];
        for (int j = de.size() - 1; j >= 0; j--){
            result[j] = de.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        // asteroidCollision(new int[] { 3, 5, -6, 2, -1, 4 });
        // System.out.println(asteroidCollision(new int[] { 5,10,-5 }));
        System.out.println(asteroidCollision(new int[] { 5, -5 }));
    }
}