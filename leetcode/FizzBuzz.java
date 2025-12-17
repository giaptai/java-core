package leetcode;

public class FizzBuzz {

    // solution 1
    public static void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }

    // solution 2
    public static void fizzBuzz2(int n) {
        for (int i = 1; i <= n; i++) {
            String result = "";
            if (i % 3 == 0)
                result += "Fizz";
            if (i % 5 == 0)
                result += "Buzz";
            System.out.println(result.isEmpty() ? i : result);
        }
    }

    public static void main(String[] args) {
        int case1 = 15;
        fizzBuzz(case1);
    }
}
