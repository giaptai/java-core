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

    String decToBinary(int num, String value){
        if (num == 0){
            return value;
        }
        int mod = num % 2 == 0 ? 0 : 1;
        return decToBinary(num / 2, mod + value); 
    }
    
    int decToBinary1(int n){
        if(n == 0){
            return 0;
        }
        return n % 2 + 10 * decToBinary1(n/2);
    }

    public static void main(String[] args) {
        // System.out.println(new Test().sumOfDigit(10000));
        // System.out.println(new Test().powerOfNum(3, 3));
        System.out.println(new Test().decToBinary(29, ""));
        System.out.println(new Test().decToBinary1(17));
    }
}
