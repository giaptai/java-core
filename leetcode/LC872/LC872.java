package leetcode.LC872;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

import org.w3c.dom.Node;

public class LC872 {
    public boolean leafSimilar(Node root1, Node root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        fds(root1, l1);
        fds(root2, l2);
        return l1.equals(l2);
    }

    void fds(Node root, List<Integer> l) {
        if (root == null) {
            return;
        }
        if (root.left == null && null == root.right) {
            l.add(root.val);
        }
        fds(root.left, l);
        fds(root.right, l);
    }
}
