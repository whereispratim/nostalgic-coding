# Binary Tree Traversals

## Problem Description

Given a binary tree, perform various traversals: Inorder, Preorder, Postorder, and Level Order.

## Solution
<table>
<tr>
<th>Python</th>
<th>Java</th>
</tr>
<tr>
<td>
<pre><code class="python">
from collections import deque

class TreeNode:
def __init__(self, val=0, left=None, right=None):
self.val = val
self.left = left
self.right = right

class Solution:
def inorderTraversal(self, root):
result = []
self.inorder(root, result)
return result

    def inorder(self, root, result):
        if root:
            self.inorder(root.left, result)
            result.append(root.val)
            self.inorder(root.right, result)
    
    def preorderTraversal(self, root):
        result = []
        self.preorder(root, result)
        return result
    
    def preorder(self, root, result):
        if root:
            result.append(root.val)
            self.preorder(root.left, result)
            self.preorder(root.right, result)
    
    def postorderTraversal(self, root):
        result = []
        self.postorder(root, result)
        return result
    
    def postorder(self, root, result):
        if root:
            self.postorder(root.left, result)
            self.postorder(root.right, result)
            result.append(root.val)
    
    def levelOrder(self, root):
        result = []
        if not root:
            return result
        queue = deque([root])
        while queue:
            level = []
            for _ in range(len(queue)):
                node = queue.popleft()
                level.append(node.val)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)
            result.append(level)
        return result

root = TreeNode(50)
root.left = TreeNode(30)
root.right = TreeNode(70)
root.left.left = TreeNode(20)
root.left.right = TreeNode(40)
root.right.left = TreeNode(60)
root.right.right = TreeNode(80)

solution = Solution()
print("Inorder traversal:", solution.inorderTraversal(root))
print("Preorder traversal:", solution.preorderTraversal(root))
print("Postorder traversal:", solution.postorderTraversal(root))
print("Level order traversal:", solution.levelOrder(root))
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode(int val) { this.val = val; }
}

class Solution {
public List<Integer> inorderTraversal(TreeNode root) {
List<Integer> result = new ArrayList<>();
inorder(root, result);
return result;
}

    private void inorder(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorder(root.left, result);
            result.add(root.val);
            inorder(root.right, result);
        }
    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorder(root, result);
        return result;
    }
    
    private void preorder(TreeNode root, List<Integer> result) {
        if (root != null) {
            result.add(root.val);
            preorder(root.left, result);
            preorder(root.right, result);
        }
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }
    
    private void postorder(TreeNode root, List<Integer> result) {
        if (root != null) {
            postorder(root.left, result);
            postorder(root.right, result);
            result.add(root.val);
        }
    }
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.right = new TreeNode(70);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);

        Solution solution = new Solution();
        System.out.println("Inorder traversal: " + solution.inorderTraversal(root));
        System.out.println("Preorder traversal: " + solution.preorderTraversal(root));
        System.out.println("Postorder traversal: " + solution.postorderTraversal(root));
        System.out.println("Level order traversal: " + solution.levelOrder(root));
    }
}
</code></pre>
</td>
</tr>
</table>

## Explanation of Traversals

Given the binary tree structure:
```
      50
     /  \
   30    70
   / \   / \
 20  40 60  80

```

### Inorder Traversal (Left → Root → Right):
- Visits nodes in non-decreasing order.
- Output: 20, 30, 40, 50, 60, 70, 80

### Preorder Traversal (Root → Left → Right):
- Useful for creating a copy of the tree.
- Output: 50, 30, 20, 40, 70, 60, 80

### Postorder Traversal (Left → Right → Root):
- Useful for deleting the tree.
- Output: 20, 40, 30, 60, 80, 70, 50

### Level Order Traversal (Breadth-First):
- Visits nodes level by level, starting from the root.
- Output: 50, 30, 70, 20, 40, 60, 80

### Key Points:
- Inorder traversal gives elements in sorted order for BSTs.
- Preorder traversal can recreate the tree structure.
- Postorder traversal is useful for cleanup operations.
- Level Order traversal is helpful for scenarios where processing nodes level-wise is required.

## Complexity Analysis

For all traversals:
- Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once.
- Space Complexity:
    - O(h) for recursive implementations, where h is the height of the tree. In the worst case (skewed tree), this can be O(n).
    - O(n) for level order traversal due to the queue used.

## LeetCode Links

- [94. Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)
- [144. Binary Tree Preorder Traversal](https://leetcode.com/problems/binary-tree-preorder-traversal/)
- [145. Binary Tree Postorder Traversal](https://leetcode.com/problems/binary-tree-postorder-traversal/)
- [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)