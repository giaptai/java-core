package leetcode;

import java.util.Arrays;

public class MergeTwoSortedList {

    // solution 1
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length - n == m) {
            for (int i = 0; i < nums2.length; i++) { // O(n)
                if (m < nums1.length) {
                    nums1[m] = nums2[i];
                    m++;
                }
            }
        }
        Arrays.sort(nums1); // O(nlogn)
        return nums1;
    }

    // solution 2 - two pointers (duyệt từ CUỐI về đầu)
    // Time: O(m+n), Space: O(1)
    public static int[] merge2(int[] nums1, int m, int[] nums2, int n) {
        // Bước 1: Khởi tạo 3 pointers
        int i = m - 1;     // Pointer cuối nums1 (phần có số thật) - VD: index 2 trong [1,2,3,0,0,0]
        int j = n - 1;     // Pointer cuối nums2 - VD: index 2 trong [2,5,6]
        int k = m + n - 1; // Pointer cuối nums1 (toàn bộ) - VD: index 5 trong [1,2,3,0,0,0]

        // Bước 2: Duyệt từ cuối về đầu, merge 2 mảng
        // Tại sao duyệt từ cuối? Vì nums1 có chỗ trống ở cuối, điền từ cuối không ghi đè lên số đang dùng
        while (j >= 0) {  // Dừng khi hết nums2 (vì nums1 còn lại đã đúng vị trí)
            // So sánh 2 phần tử hiện tại, chọn số LỚN HƠN điền vào cuối
            if (i >= 0 && nums1[i] > nums2[j]) {  // i >= 0 để tránh lỗi khi nums1 hết trước
                nums1[k] = nums1[i];  // Chọn nums1[i] vì lớn hơn
                i--;                   // Dịch pointer i sang trái
            } else {
                nums1[k] = nums2[j];  // Chọn nums2[j] (hoặc vì lớn hơn, hoặc vì nums1 đã hết)
                j--;                   // Dịch pointer j sang trái
            }
            k--;  // Dịch pointer k sang trái (chuẩn bị điền vị trí tiếp theo)
        }

        // Bước 3: Return nums1 đã merge
        // Lưu ý: Nếu nums1 còn phần tử (i >= 0) thì không cần xử lý vì đã đúng vị trí
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        // System.out.println(Arrays.toString(merge(nums1, 3, nums2, 3)));
        System.out.println(Arrays.toString(merge2(nums1, 3, nums2, 3)));
    }
}
