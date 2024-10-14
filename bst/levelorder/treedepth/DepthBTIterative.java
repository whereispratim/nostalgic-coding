package levelorder.treedepth;

import java.util.LinkedList;
import java.util.Queue;


class DepthBTIterative {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;  // Increase depth for each level

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                // Add left and right children to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        DepthBTIterative solution = new DepthBTIterative();

        // Example 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(solution.maxDepth(root1));  // Output: 3

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println(solution.maxDepth(root2));  // Output: 2
    }
}

