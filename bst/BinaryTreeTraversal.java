import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition of the Node class for the binary tree
class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

// Binary Tree class to perform different tree traversals
public class BinaryTreeTraversal {
    Node root;

    // Inorder Traversal (Left -> Root -> Right)
    void inorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);  // Visit left subtree
        System.out.print(node.data + " ");  // Visit root node
        inorderTraversal(node.right);  // Visit right subtree
    }

    // Preorder Traversal (Root -> Left -> Right)
    void preorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");  // Visit root node
        preorderTraversal(node.left);  // Visit left subtree
        preorderTraversal(node.right);  // Visit right subtree
    }

    // Postorder Traversal (Left -> Right -> Root)
    void postorderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postorderTraversal(node.left);  // Visit left subtree
        postorderTraversal(node.right);  // Visit right subtree
        System.out.print(node.data + " ");  // Visit root node
    }

    // Level Order Traversal (Breadth-First)
    void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.print(currentNode.data + " ");  // Visit node

            // Add left child to queue
            if (currentNode.left != null) {
                queue.add(currentNode.left);
            }

            // Add right child to queue
            if (currentNode.right != null) {
                queue.add(currentNode.right);
            }
        }
    }

    // OR Level Order Traversal (Breadth-First)
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                currentLevel.add(node.data);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreeTraversal tree = new BinaryTreeTraversal();

        // Construct the binary tree
        tree.root = new Node(50);
        tree.root.left = new Node(30);
        tree.root.right = new Node(70);
        tree.root.left.left = new Node(20);
        tree.root.left.right = new Node(40);
        tree.root.right.left = new Node(60);
        tree.root.right.right = new Node(80);

        System.out.println("Inorder traversal (Left -> Root -> Right): ");
        tree.inorderTraversal(tree.root);  // Expected: 20 30 40 50 60 70 80
        System.out.println("\n");

        System.out.println("Preorder traversal (Root -> Left -> Right), DFS: ");
        tree.preorderTraversal(tree.root);  // Expected: 50 30 20 40 70 60 80
        System.out.println("\n");

        System.out.println("Postorder traversal (Left -> Right -> Root): ");
        tree.postorderTraversal(tree.root);  // Expected: 20 40 30 60 80 70 50
        System.out.println("\n");

        System.out.println("Level order traversal (Breadth-First): ");
        tree.levelOrderTraversal();  // Expected: 50 30 70 20 40 60 80
        System.out.println();
    }
}
