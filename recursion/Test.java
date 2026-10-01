package recursion;

public class Test {
    int sumOfDigit(int num) {
        if (num < 10) {
            return num;
        }
        return sumOfDigit(num / 10) + num % 10;
    }

    public static void main(String[] args) {
        System.out.println(new Test().sumOfDigit(10000));
    }
}
