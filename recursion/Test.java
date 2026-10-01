package recursion;

public class Test {
    int sumOfDigit(int num) {
        if (num < 10) {
            return num;
        }
        return sumOfDigit(num / 10) + num % 10;
    }

    int powerOfNum(int base, int num) {
        if (num == 0) {
            return 1;
        }
        if (num == 1) {
            return base;
        }
        return base * powerOfNum(base, num - 1);
    }

    int gcd(int a, int b){
        if (b == 0){
            return a;
        }
        return gcd(b, a%b);
    }

    public static void main(String[] args) {
        System.out.println(new Test().sumOfDigit(10000));
        System.out.println(new Test().powerOfNum(3, 3));
    }
}
