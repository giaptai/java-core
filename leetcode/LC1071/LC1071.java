package LC1071;

/*
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

Example 1:
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"

Example 2:
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"

Example 3:
Input: str1 = "LEET", str2 = "CODE"
Output: ""

Example 4:
Input: str1 = "AAAAAB", str2 = "AAA"
Output: ""​​​​​​​

Constraints:

    1 <= str1.length, str2.length <= 1000
    str1 and str2 consist of English uppercase letters.
*/

public class LC1071 {
    public static String gcdOfStrings0(String str1, String str2) {
        // if they have greatest common divisor two strings will have commutative property
        if ( !(str1 + str2).equals(str2 + str1) ){
            return "";
        }
        int gcdLen = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLen);
    }
    public static int gcd(int len1, int len2) {
        // Euclid
        return len2 == 0 ? len1 : gcd(len2, len1 % len2); 
    }

    public static String gcdOfStrings2(String str1, String str2) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        String flag = str1.length() > str2.length() ? str2 : str1;
        // get prefix  
        for(int e =0 ;e < flag.length(); e++){
            sb.append(flag.charAt(e));
            int len = sb.length();

            if (str1.length() % len == 0 && str2.length() % len == 0){
                int lens1 = str1.length() / len;
                int lens2 = str2.length() / len;

                StringBuilder s1 = new StringBuilder();
                for(int i=0; i < lens1; i++){
                    s1.append(sb);
                }

                StringBuilder s2 = new StringBuilder();
                for(int j=0; j < lens2;  j++){
                    s2.append(sb);
                }

                if (s1.toString().equals(str1) && s2.toString().equals(str2)){
                    result = sb.toString();
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        // System.out.println(LC1071.gcdOfStrings("ABABAB", "ABAB"));
        System.out.println(LC1071.gcdOfStrings0("LEET", "CODE"));
    }
}
