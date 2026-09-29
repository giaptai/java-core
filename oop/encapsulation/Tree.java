package oop.encapsulation;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// class TreeNode {
//     int val;
//     List<TreeNode> children;

//     TreeNode(int val) {
//         this.val = val;
//         children = new ArrayList<>();
//     }

//     void addChild(TreeNode node) {
//         children.add(node);
//     }

//     String print(int level) {
//         String ret;
//         ret = " ".repeat(level) + val + "\n";
//         for (TreeNode node : children) {
//             ret += node.print(level + 1);
//         }
//         return ret;
//     }
// }
class Node {
    int val;
    Node left;
    Node right;
    int height;

    Node(int val) {
        this.val = val;
    }
}

class BinaryTree {
    Node root = null;

    // PreOrder
    void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // InOrder
    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.val + " ");
        inOrder(node.right);
    }

    // PostOrder
    void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val + " ");
    }

    // LevelOrder
    void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.remove();
            System.out.printf("%d ", n.val);
            if (n.left != null) {
                q.add(n.left);
            }
            if (n.right != null) {
                q.add(n.right);
            }
        }
    }
}

class BinaryTreeArr {
    int[] nodes;
    int lastInx;

    BinaryTreeArr(int size) {
        nodes = new int[size + 1];
    }

    boolean isFull() {
        return lastInx >= nodes.length - 1;
    }

    void insert(int val) {
        if (isFull()) {
            return;
        }
        nodes[lastInx + 1] = val;
        lastInx++;
    }

    // pre Order
    void preOrder(int i) {
        if (i > lastInx) {
            return;
        }
        System.out.printf("%s ", nodes[i]);
        preOrder(2 * i);
        preOrder(2 * i + 1);
    }

    // in Order
    void inOrder(int i) {
        if (i > lastInx) {
            return;
        }
        inOrder(2 * i);
        System.out.printf("%s ", nodes[i]);
        inOrder(2 * i + 1);
    }

    // post Order
    void postOrder(int i) {
        if (i > lastInx) {
            return;
        }
        postOrder(2 * i);
        postOrder(2 * i + 1);
        System.out.printf("%s ", nodes[i]);
    }

    // level order
    void levelOrder() {
        for (int i = 1; i <= lastInx; i++) {
            System.out.printf("%s ", nodes[i]);
        }
        System.out.println();
    }

    int search(int val) {
        for (int i = 1; i <= lastInx; i++) {
            if (nodes[i] == val) {
                return i;
            }
        }
        return -1;
    }

    void delete(int val){
        for (int i = 1; i <= lastInx; i++) {
            if (nodes[i] == val) {
                nodes[i] = nodes[lastInx];
                nodes[lastInx] = 0;
                lastInx--;
            }
        }
    }
    void deleteALl(){
        nodes = new int[lastInx + 1];
        lastInx = 0;
    }
}

public class Tree {

    public static void main(String[] args) {
        BinaryTreeArr binaryTreeArr = new BinaryTreeArr(9);
        binaryTreeArr.insert(1);
        binaryTreeArr.insert(2);
        binaryTreeArr.insert(3);
        binaryTreeArr.insert(4);
        binaryTreeArr.insert(5);
        binaryTreeArr.insert(6);
        binaryTreeArr.insert(7);
        binaryTreeArr.insert(8);
        binaryTreeArr.insert(9);
        // System.out.println(Arrays.toString(binaryTreeArr.nodes));
        // binaryTreeArr.preOrder(1);
        // binaryTreeArr.inOrder(1);
        // binaryTreeArr.postOrder(1);
        // binaryTreeArr.levelOrder();
        binaryTreeArr.delete(3);
        System.out.println(Arrays.toString(binaryTreeArr.nodes));
        // Node r = new Node(1);
        // Node r1 = new Node(2);
        // Node r2 = new Node(3);
        // Node r3 = new Node(4);
        // Node r4 = new Node(5);
        // Node r5 = new Node(6);
        // Node r6 = new Node(7);
        // Node r7 = new Node(8);
        // Node r8 = new Node(9);
        // r.left = r1;
        // r.right = r2;
        // r1.left = r3;
        // r1.right = r4;
        // r3.left = r7;
        // r3.right = r8;
        // r2.left = r5;
        // r2.right = r6;
        // BinaryTree binaryTree = new BinaryTree();
        // binaryTree.root = r;
        // binaryTree.preOrder(r);
        // binaryTree.inOrder(r);
        // binaryTree.postOrder(r);
        // binaryTree.levelOrder(r);

        // TreeNode n = new TreeNode(0);
        // TreeNode n1 = new TreeNode(1);
        // TreeNode n2 = new TreeNode(2);
        // TreeNode n3 = new TreeNode(3);
        // TreeNode n4 = new TreeNode(4);
        // n.addChild(n1);
        // n.addChild(n2);
        // n1.addChild(n3);
        // n1.addChild(n4);
        // System.out.println(n.print(0));
    }
}
