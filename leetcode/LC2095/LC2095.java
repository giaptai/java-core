package leetcode.LC2095;

public class LC2095 {
    public static ListNode deleteMiddle(ListNode head) {
        if (head ==null || head.next == null){
            return null;
        }

        ListNode prev = null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        prev.next = slow.next;
        return head;
    }
    
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.addFirst(head, new ListNode(2));
        head.addFirst(head, new ListNode(3));
        head.addFirst(head, new ListNode(4));
        deleteMiddle(head);
        ListNode temp = head;
        while (temp != null){
            System.out.printf("%d ->",temp.val);
            temp = temp.next;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
    void addFirst(ListNode head, ListNode node){
        while(head.next != null){
            head = head.next;
        }
        head.next = node;
    }
}