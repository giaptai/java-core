package leetcode;

import java.util.Set;
import java.util.HashSet;

public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        // 1. each row must contains the digits 1-9 without duplicates
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (set.contains(c)) {
                    return false;
                } else if (c != '.') {
                    set.add(c);
                }
            }
        }

        // 2. each column must contains the digits 1-9 without duplicates
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                char c = board[j][i];
                if (set.contains(c)) {
                    return false;
                } else if (c != '.') {
                    set.add(c);
                }
            }
        }

        // Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9
        // without duplicates.

        // coordinates
        int[][] subBoxes = {
                { 0, 0 }, { 0, 3 }, { 0, 6 },
                { 3, 0 }, { 3, 3 }, { 3, 6 },
                { 6, 0 }, { 6, 3 }, { 6, 6 }
        };

        for (int i = 0; i < subBoxes.length; i++) {
            int init_c = subBoxes[i][0];
            int init_r = subBoxes[i][1];
            Set<Character> set = new HashSet<>();
            for (int j = init_c; j < init_c + 3; j++) {
                for (int k = init_r; k < init_r + 3; k++) {
                    char c = board[j][k];
                    if (set.contains(c)) {
                        return false;
                    } else if (c != '.') {
                        set.add(c);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] case1 = {
                { '1', '2', '.', '.', '3', '.', '.', '.', '.' },
                { '4', '.', '.', '5', '.', '.', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '.', '3' },
                { '5', '.', '.', '.', '6', '.', '.', '.', '4' },
                { '.', '.', '.', '8', '.', '3', '.', '.', '5' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '.', '.', '.', '.', '.', '2', '.', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '8' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        char[][] case2 = {
                { '1', '2', '.', '.', '3', '.', '.', '.', '.' },
                { '4', '.', '.', '5', '.', '.', '.', '.', '.' },
                { '.', '9', '1', '.', '.', '.', '.', '.', '3' },
                { '5', '.', '.', '.', '6', '.', '.', '.', '4' },
                { '.', '.', '.', '8', '.', '3', '.', '.', '5' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '.', '.', '.', '.', '.', '2', '.', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '8' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        System.out.println(isValidSudoku(case1));
        System.out.println(isValidSudoku(case2));
    }
}
