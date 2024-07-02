import java.util.ArrayList;
import java.util.List;

public class BST {
    Node root;

    public BST() {
        root = null;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.val)
            root.left = insertRec(root.left, key);
        else if (key > root.val)
            root.right = insertRec(root.right, key);

        return root;
    }

    public List<Integer> inorder() {
        List<Integer> traversal = new ArrayList<>();
        inorderRec(root, traversal);
        return traversal;
    }

    private void inorderRec(Node root, List<Integer> traversal) {
        if (root != null) {
            inorderRec(root.left, traversal);
            traversal.add(root.val);
            inorderRec(root.right, traversal);
        }
    }

    public List<Integer> preorder() {
        List<Integer> traversal = new ArrayList<>();
        preorderRec(root, traversal);
        return traversal;
    }

    private void preorderRec(Node root, List<Integer> traversal) {
        if (root != null) {
            traversal.add(root.val);
            preorderRec(root.left, traversal);
            preorderRec(root.right, traversal);
        }
    }

    public List<Integer> postorder() {
        List<Integer> traversal = new ArrayList<>();
        postorderRec(root, traversal);
        return traversal;
    }

    private void postorderRec(Node root, List<Integer> traversal) {
        if (root != null) {
            postorderRec(root.left, traversal);
            postorderRec(root.right, traversal);
            traversal.add(root.val);
        }
    }
}

