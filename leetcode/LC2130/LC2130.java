package leetcode.LC2130;

class Node {
    int val;
    Node next;

    Node() {
    }

    Node(int val) {
        this.val = val;
    }

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

public class LC2130 {
    public int pairSum(Node head) {
        Node s = head;
        Node f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next;
        }
        Node prev = null;
        Node curr = s;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        Node t1 = head;
        Node t2 = prev;
        int max = 0;
        while (t1 != null && t2 != null) {
            max = Math.max(t1.val + t2.val, max);
            t1 = t1.next;
            t2 = t2.next;
        }
        return max;
    }

    public static void main(String[] args) {
        
    }
}
