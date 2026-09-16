package leetcode.LC206;

public class LC206 {
    public ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode result = null;
        while (temp != null) {
            if (result == null) {
                result = new ListNode(temp.val);
            } else {
                ListNode a = new ListNode(temp.val);
                a.next = result;
                result = a;
            }
            temp = temp.next;
        }
        return result;
    }
}
