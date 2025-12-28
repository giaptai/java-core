package leetcode;

import java.util.Stack;
import java.util.Arrays;

/**
 * Có n xe ô tô di chuyển đến cùng 1 điểm trên 1 dường cao tốc
 * Bạn được cho 2 mảng số nguyên: position và speed, cả 2 đều có độ dài n
 * - position[i]: là vị trí thứ ith của xe (tính theo dặm)
 * - speed[i]: là tốc độ của xe thứ ith (tính theo dặm mỗi giờ)
 * Đích đến là vị trí dặm mục tiêu
 * 
 * Một xe ô tô không được vượt qua xe khác trước nó. Chỉ có thể bắt kịp xe khác
 * và
 * chạy cùng một tốc độ với xe trước nó
 * 
 * Một đội xe là một tập hợp không rỗng chạy cùng một vị trí và cùng một tốc độ.
 * Một xe ô tô đơn cũng coi như là một đội xe
 * 
 * Nếu một xe ô tô bắt kịp 1 đội xe ngay khi đội xe đến đích thì xe đó được coi
 * là một phần của đội xe
 * 
 * Trả về số lượng đội xe khác nhau cùng đến một điểm đich
 */
public class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        int len = position.length;
        double[] time = new double[len];
        Stack<Integer> st = new Stack<>();
        // calculate time
        for (int i = 0; i < len; i++) {
            time[i] = (double) (target - position[i]) / speed[i];
        }

        // create idx stores positions at descending
        Integer[] idx = new Integer[len];
        for (int j = 0; j < len; j++) {
            idx[j] = j;
        }
        Arrays.sort(idx, (a, b) -> Integer.compare(position[b], position[a]));

        // comparing time, if curr car's time has less then car's time in stack 
        // it mean it will catch up the hedd up car so they are car fleet.
        for(Integer k : idx){
            if(st.isEmpty() || time[k] > time[st.peek()]){
                st.push(k);
            }
        }
        return st.size();
    }

    public static void main(String[] args) {
        int[] case_pos1 = {10,8,0,5,3};
        int[] case_sp1 = {2,4,1,1,3};
        int case_tar = 12;
        System.out.println(carFleet(case_tar, case_pos1,case_sp1));
    }
}
