package leetcode.LC206;

public class LC206 {
    public static ListNode reverseList(ListNode head) {
        ListNode temp = head;
        ListNode curr = head;
        ListNode prev = null;

        while (temp != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

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
