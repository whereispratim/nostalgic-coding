package levelorder;

import java.util.*;

public class BFSLevelOrderTreeBetterOutput {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // Result list to hold values of nodes at each level
        List<List<Integer>> levels = new ArrayList<>();

        // Base case: If the root is null, return an empty list
        if (root == null) {
            return levels;
        }

        // Queue to help in BFS traversal, starting with the root node
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        // Process all nodes level by level
        while (!nodeQueue.isEmpty()) {
            // List to store nodes at the current level
            List<Integer> currentLevel = new ArrayList<>();

            // Number of nodes in the current level
            int levelSize = nodeQueue.size();

            // Process each node in the current level
            for (int i = 0; i < levelSize; i++) {
                // Get the next node from the queue
                TreeNode currentNode = nodeQueue.poll();

                // Add the node's value to the current level list
                currentLevel.add(currentNode.val);

                // Add the left child to the queue if it exists
                if (currentNode.left != null) {
                    nodeQueue.add(currentNode.left);
                }

                // Add the right child to the queue if it exists
                if (currentNode.right != null) {
                    nodeQueue.add(currentNode.right);
                }
            }

            // After processing the current level, add it to the result list
            levels.add(currentLevel);
        }

        // Return the list of levels
        return levels;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        // Constructing the binary tree shown in the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        // Running the level-order traversal
        BFSLevelOrderTreeBetterOutput solution = new BFSLevelOrderTreeBetterOutput();
        List<List<Integer>> result = solution.levelOrder(root);

        // Printing the result
        System.out.println(result);
    }
}

