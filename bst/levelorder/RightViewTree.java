package levelorder;

import java.util.*;

public class RightViewTree {

    // Definition for a binary tree node.
    private static class TreeNode {
        int value;
        TreeNode leftChild, rightChild;

        TreeNode(int value) {
            this.value = value;
            this.leftChild = this.rightChild = null;
        }
    }
    // Function to return the right view of the binary tree
    public List<Integer> getRightSideView(TreeNode root) {
        List<Integer> rightViewNodes = new ArrayList<>();

        // Base case: if the root is null, return an empty list
        if (root == null) {
            return rightViewNodes;
        }

        // Queue for performing level-order traversal (BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);  // Add the root node to the queue

        // Perform BFS
        while (!queue.isEmpty()) {
            int nodesAtCurrentLevel = queue.size();  // Get the number of nodes at the current level
            TreeNode currentNode = null;  // To store the last node at the current level

            // Iterate through all nodes at the current level
            for (int i = 0; i < nodesAtCurrentLevel; i++) {
                currentNode = queue.poll();  // Dequeue a node

                // Add the left child to the queue if it exists
                if (currentNode.leftChild != null) {
                    queue.offer(currentNode.leftChild);
                }

                // Add the right child to the queue if it exists
                if (currentNode.rightChild != null) {
                    queue.offer(currentNode.rightChild);
                }
            }

            // The last node in this level is the rightmost node, so add it to the right view list
            rightViewNodes.add(currentNode.value);
        }

        // Return the list of nodes visible from the right side
        return rightViewNodes;
    }

    // Main method for testing the solution
    public static void main(String[] args) {
        // Example tree structure:
        //        1
        //      /   \
        //     2     3
        //    / \     \
        //   4   5     6
        //      / \
        //     7   8

        // Constructing the binary tree
        TreeNode root = new TreeNode(1);
        root.leftChild = new TreeNode(2);
        root.rightChild = new TreeNode(3);
        root.leftChild.leftChild = new TreeNode(4);
        root.leftChild.rightChild = new TreeNode(5);
        root.rightChild.rightChild = new TreeNode(6);
        root.leftChild.rightChild.leftChild = new TreeNode(7);
        root.leftChild.rightChild.rightChild = new TreeNode(8);

        // Create the solution object and get the right view of the tree
        RightViewTree solution = new RightViewTree();
        List<Integer> rightView = solution.getRightSideView(root);

        // Print the right view of the tree
        System.out.println("Right View of the Binary Tree: " + rightView);
    }
}

