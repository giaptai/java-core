package leetcode.LC328;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    void addFirst(ListNode head, ListNode node) {
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    void display(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.printf("%s -> ", temp.val);
            temp = temp.next;
        }
        System.out.println();
    }
}

public class LC328 {
    // optimized
    public static ListNode oddEvenList0(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = odd.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode temp = head;
        ListNode odd = null;
        ListNode result = odd;
        ListNode n = null;
        int count = 1;
        while (temp != null) {
            n = new ListNode(temp.val);
            if (count % 2 != 0) {
                if (odd == null) {
                    odd = n;
                    result = odd;
                } else {
                    odd.next = n;
                    odd = odd.next;
                }
            }
            temp = temp.next;
            count++;
        }
        count = 1;
        temp = head;
        while (temp != null) {
            n = new ListNode(temp.val);
            if (count % 2 == 0) {
                odd.next = n;
                odd = odd.next;
            }
            temp = temp.next;
            count++;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        head.addFirst(head, n2);
        head.addFirst(head, n3);
        head.addFirst(head, n4);
        head.addFirst(head, n5);

        head.display(head);

        oddEvenList(head);
    }
}