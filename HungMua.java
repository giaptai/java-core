import java.util.Arrays;

public class HungMua {
    public static int hm0(int[] height) {
        int maxTemp = 0;
        int result = 0;
        int[] maxLeft = new int[height.length];
        // max left
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || height[i] >= maxTemp) {
                maxTemp = height[i];
                continue;
            }
            maxLeft[i] = maxTemp;
        }
        maxTemp = 0;
        // right
        for (int i = height.length - 1; i >= 0; i--) {
            if (i == height.length - 1) {
                maxTemp = height[i];
                continue;
            }
            if (height[i] >= maxTemp) {
                maxTemp = height[i];
            }
            int temp = Math.min(maxTemp, maxLeft[i]);
            if (temp - height[i] > 0)
                result += temp - height[i];
        }
        return result;
    }

    public static int hm(int[] height) {
        int maxTemp = 0;
        int[] result = new int[height.length];
        int[] resultLeft = new int[height.length];
        int[] resultRight = new int[height.length];
        // left
        for (int i = 0; i < height.length; i++) {
            if (i == 0) {
                maxTemp = height[i];
                resultLeft[i] = 0;
                continue;
            }
            if (height[i] < maxTemp) {
                resultLeft[i] = maxTemp - height[i];
            } else {
                maxTemp = height[i];
            }
        }
        maxTemp = 0;
        // right
        for (int i = height.length - 1; i >= 0; i--) {
            if (i == height.length - 1) {
                maxTemp = height[i];
                resultRight[i] = 0;
                continue;
            }
            if (height[i] < maxTemp) {
                resultRight[i] = maxTemp - height[i];
            } else {
                maxTemp = height[i];
            }
        }
        for (int i = 0; i < resultRight.length; i++) {
            result[i] = resultLeft[i] < resultRight[i] ? resultLeft[i] : resultRight[i];
        }
        return Arrays.stream(result).sum();
    }

    public static String commonLongestStr(String a, String b) {
        StringBuilder result = new StringBuilder();
        int n = Math.min(a.length(), b.length());
        for (int i = 0; i < n; i++) {
            int pos1 = 0;
            int pos2 = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    pos1 = i;
                    pos2 = j;
                    break;
                }
            }

            while (pos1 < a.length() && pos2 < b.length()) {
                if (a.charAt(pos1) == b.charAt(pos2)) {
                    sb.append(a.charAt(pos1));
                    pos1++;
                    pos2++;
                }else{
                    break;
                }

            }
            result = sb.length() > result.length() ? sb : result;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        // System.out.println(hm0(new int[] { 2, 1, 1, 2, 3, 1, 2, 1, 3 }));
        System.out.println(commonLongestStr("abca", "xaabc"));
    }
}
