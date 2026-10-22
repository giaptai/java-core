import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;

class Node {
    int val;
    Node left;
    Node right;
    int height;

    Node(int val) {
        this.val = val;
        height = 1;
    }
}

class BinaryTree {

    // preOder
    void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.printf("%d ", node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    // inorder
    void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.printf("%d ", node.val);
        inOrder(node.right);
    }

    // postorder
    void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.printf("%d ", node.val);
    }

    // level order
    void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.printf("%d ", n.val);
            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }
        }
        System.out.println();
    }

    // search
    void search(Node node, int val) {
        if (node == null) {
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.val == val) {
                System.out.println("The value is found");
                return;
            }
            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }
        }
        System.out.println("The value is not found");
    }

    Node insert(Node root, Node node) {
        if (root == null) {
            root = node;
            return root;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            if (n.left == null) {
                n.left = node;
                return root;
            }
            q.offer(n.left);
            if (n.right == null) {
                n.right = node;
                return root;
            }
            q.offer(n.right);
        }
        return root;
    }

    Node removeDeepestNode(Node root) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        Node prev = null;
        Node curr = null;
        Node deep = null;
        while (!q.isEmpty()) {
            prev = curr;
            curr = q.poll();
            if (curr.left == null) {
                deep = prev.right;
                prev.right = null; // do lúc này prev right là node sâu nhất
                return deep;
            } else if (curr.right == null) {
                deep = curr.left;
                curr.left = null; // lúc nàY LEFT CHILD là node sâu nhất
                return deep;
            }
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return deep;
    }

    Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root.val == value ? null : root;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.val == value) {
                Node node = removeDeepestNode(root);
                curr.val = node.val;
                return root;
            } else {
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
        }
        return root;
    }

    Node deleteAll(Node root) {
        root = null;
        return root;
    }
}

class AVLTree {
    Node root;

    AVLTree() {
        root = null;
    }

    AVLTree(int value) {
        this.root = new Node(value);
    }

    // preOrder
    void preOrder() {
        if (root == null) {
            return;
        }
        Stack<Node> st = new Stack<>();
        st.add(root);
        while (!st.isEmpty()) {
            Node n = st.pop();
            System.out.printf("%d ", n.val);
            if (n.right != null) {
                st.push(n.right);
            }
            if (n.left != null) {
                st.push(n.left);
            }
        }
    }

    // in Order
    void inOrder() {
        if (root == null) {
            return;
        }
        Stack<Node> st = new Stack<>();
        Node curr = root;
        while (curr != null || !st.isEmpty()) {
            while (curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            System.out.printf("%d ", curr.val);
            curr = curr.right;
        }
        System.out.println();
    }

    void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.printf("%d ", n.val);
            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }
        }
        System.out.println();
    }

    int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * Performs a right rotation on the given node.
     * The left child becomes the new root of the subtree.
     *
     * @param node the root of the subtree to rotate
     * @return the new root after rotation
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = node.left.right;
        newRoot.right = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    /**
     * Performs a left rotation on the given node.
     * The right child becomes the new root of the subtree.
     *
     * @param node the root of the subtree to rotate
     * @return the new root after rotation
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = node.right.left;
        newRoot.left = node;
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        newRoot.height = 1 + Math.max(getHeight(newRoot.left), getHeight(newRoot.right));
        return newRoot;
    }

    int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node insertNode(Node node, int value) {
        if (node == null) {
            return new Node(value);
        } else if (value > node.val) {
            node.right = insertNode(node.right, value);
        } else {
            node.left = insertNode(node.left, value);
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        int balance = getBalance(node);

        // left left
        if (balance > 1 && value < node.left.val) {
            return rotateRight(node);
        }
        // left right
        if (balance > 1 && value > node.left.val) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // right right
        if (balance < -1 && value > node.right.val) {
            return rotateLeft(node);
        }

        // right left
        if (balance < -1 && value < node.right.val) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void insert(int value) {
        root = insertNode(root, value);
    }

    /**
     * Find the minimum node
     */
    static Node minimumNode(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimumNode(root.left);
    }

    private Node deleteNode(Node node, int value) {
        if (node == null) {
            return node;
        }
        if (node.val < value) {
            node.right = deleteNode(node.right, value);
        } else if (node.val > value) {
            node.left = deleteNode(node.left, value);
        } else {
            // node deleted has 2 child
            if (node.left != null && node.right != null) {
                Node temp = node;
                Node minimumNode = minimumNode(temp.right);
                node.val = minimumNode.val;
                node.right = deleteNode(node.right, minimumNode.val);
            } else if (node.left != null) { // node deleted has one child
                node = node.left;
            } else if (node.right != null) { // node deleted has one child
                node = node.right;
            } else { // node deleted is a leaf
                node = null;
            }
        }
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }

        int balance = getBalance(node);
        // left left
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }
        // left right
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // right right
        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        // right left
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    void delete(int value) {
        root = deleteNode(root, value);
    }

    void deleteAll() {
        root = null;
    }
}

public class Test {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree(1);
        avlTree.insert(2);
        avlTree.insert(3);
        avlTree.insert(4);
        avlTree.insert(5);
        avlTree.insert(6);
        avlTree.levelOrder();
        System.out.println(avlTree.root.height);
        avlTree.delete(1);
        avlTree.delete(3);
        avlTree.delete(6);
        // avlTree.delete(2);
        avlTree.levelOrder();
        System.out.println(avlTree.root.height);
        System.out.println(avlTree.root.height);
    }
}
