## Maximum Depth of Binary Tree

### Problem Statement

Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
To determine the maximum depth of a binary tree, you can use a depth-first search (DFS) approach, either recursively or iteratively.

### Examples

#### Example 1:

Input: root = [3,9,20,null,null,15,7]
Output: 3

#### Example 2:

Input: root = [1,null,2]
Output: 2

### Approach

We'll use a recursive approach:
1. If the root is null, return 0.
2. Recursively calculate the maximum depth of the left and right subtrees.
3. Return the maximum of the left and right depths, plus 1 (for the current node).

### Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class DepthBTRecursive:
    def maxDepth(self, root: TreeNode) -> int:
        if not root:
            return 0
    return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1

if __name__ == "__main__":
solution = DepthBTRecursive()

    # Example 1
    root1 = TreeNode(3)
    root1.left = TreeNode(9)
    root1.right = TreeNode(20)
    root1.right.left = TreeNode(15)
    root1.right.right = TreeNode(7)
    print(solution.maxDepth(root1))  # Output: 3

    # Example 2
    root2 = TreeNode(1)
    root2.right = TreeNode(2)
    print(solution.maxDepth(root2))  # Output: 2

</code></pre>
</td>
<td>
<pre><code class="java">

class TreeNode {
int val;
TreeNode left;
TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class DepthBTRecursive {
public int maxDepth(TreeNode root) {
// Base case: if the node is null, the depth is 0
if (root == null) {
return 0;
}
// Recursively find the depth of the left and right subtrees
int leftDepth = maxDepth(root.left);
int rightDepth = maxDepth(root.right);

        // The depth of the current node is 1 + the max depth of the left and right subtrees
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        DepthBTRecursive solution = new DepthBTRecursive();

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

</code></pre>
</td>
</tr>
</table>

### Explanation

1. We start with a base case: if the root is null, the depth is 0.
2. For each non-null node:
    - We recursively calculate the maximum depth of its left subtree.
    - We recursively calculate the maximum depth of its right subtree.
    - We take the maximum of these two depths and add 1 (to account for the current node).
3. This process continues until we reach leaf nodes (nodes with no children).

### Detailed Walkthrough

Let's walk through the solution for the tree [3,9,20,null,null,15,7]:

1. Start at root (3):
    - Calculate depth of left subtree (9):
        - Left child is null, return 0
        - Right child is null, return 0
        - Depth of node 9 = max(0, 0) + 1 = 1
    - Calculate depth of right subtree (20):
        - Calculate depth of left child (15):
            - Both children are null, return 1
        - Calculate depth of right child (7):
            - Both children are null, return 1
        - Depth of node 20 = max(1, 1) + 1 = 2
    - Depth of root = max(1, 2) + 1 = 3

2. Return 3 as the maximum depth.

### Complexity Analysis

- Time Complexity: O(n), where n is the number of nodes in the tree. We visit each node exactly once.
- Space Complexity: O(h), where h is the height of the tree. This is due to the recursive call stack. In the worst case of a skewed tree, this could be O(n).

### Key Observations

1. This solution uses a depth-first search (DFS) approach.
2. The recursive nature of the solution mirrors the recursive structure of the tree itself.
3. For each node, we're essentially asking: "What's the deepest I can go from here?"
4. This solution works for both balanced and unbalanced trees.

### Detailed Explanation of the Code Iterative vs Recursive

#### TreeNode Class:
- Represents a node in the binary tree with a value and pointers to left and right children.

#### maxDepth Method:

##### Recursive Approach:
1. If the current node is null, return 0.
2. Recursively calculate the depth of the left and right subtrees.
3. Return the maximum depth of the two subtrees plus one (for the current node).

##### Iterative Approach (BFS):
1. Use a queue to perform Breadth-First Search (BFS). Start with the root node in the queue.
2. For each level of nodes:
    - Increment the depth count.
    - Process all nodes at the current level.
    - Add their children to the queue for the next level.

#### Main Method:
- Tests the maxDepth function with two examples:
    1. A tree with multiple levels: [3,9,20,null,null,15,7]
    2. A simple tree with two nodes: [1,null,2]

### Complexity Analysis

- Time Complexity: O(n) for both approaches, where n is the number of nodes in the tree.
    - We visit each node exactly once.

- Space Complexity:
    - Recursive Approach: O(h), where h is the height of the tree.
        - This is due to the recursion stack.
        - In the worst case (skewed tree), this could be O(n).
    - Iterative Approach: O(w), where w is the maximum width of the tree.
        - In the worst case (complete binary tree), this could be O(n/2) ≈ O(n).

### Key Observations

1. The recursive approach naturally follows the tree structure and is more concise.
2. The iterative approach using BFS can be more space-efficient for wide, shallow trees.
3. Both approaches work well for balanced and unbalanced trees.
4. The choice between recursive and iterative can depend on the specific requirements (e.g., stack limitations) and the expected shape of the tree.

This iterative approach uses a queue to perform a level-order traversal (BFS) of the tree, keeping track of the depth of each node. The maximum depth encountered during this traversal is the depth of the tree.

### LeetCode Link

[104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)