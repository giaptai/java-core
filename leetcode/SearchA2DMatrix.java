package leetcode;

public class SearchA2DMatrix {

    // solution 1 - m * log n
    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix[i].length - 1;
            while (left <= right) {
                int m = left + (right - left) / 2;
                if (matrix[i][m] < target) {
                    left = m + 1;
                } else if (matrix[i][m] > target) {
                    right = m - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    // solution 2 - log m * n
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;
        while (left <= right) {
            int m = left + (right - left) / 2;
            int row = m / cols;
            int col = m % cols;
            if (matrix[row][col] < target) {
                left = m + 1;
            } else if (matrix[row][col] > target) {
                right = m - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] case1 = { { 1, 2, 4, 8 }, { 10, 11, 12, 13 }, { 14, 20, 30, 40 } };
        int[][] case2 = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };

        System.out.println(searchMatrix2(case1, 1));
        System.out.println(searchMatrix2(case2, 13));
    }
}
