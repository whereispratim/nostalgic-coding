# Right View of a Binary Tree

## Problem Description

Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

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

class RightViewTree:
def rightSideView(self, root: TreeNode):
result = []

        # If the tree is empty, return an empty list
        if not root:
            return result

        # Initialize a queue for level order traversal
        queue = deque([root])

        # Iterate until the queue is empty
        while queue:
            level_size = len(queue)

            # Traverse each node in the current level
            for i in range(level_size):
                node = queue.popleft()

                # If it's the last node in the current level, add it to the result
                if i == level_size - 1:
                    result.append(node.val)

                # Add left and right children to the queue
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

        return result

if __name__ == "__main__":
root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)
root.left.left = TreeNode(4)
root.left.right = TreeNode(5)
root.right.right = TreeNode(6)
root.left.right.left = TreeNode(7)
root.left.right.right = TreeNode(8)

    # Initialize the solution and print the right view of the tree
    solution = RightViewTree()
    right_view = solution.rightSideView(root)
    print("Right View of the Binary Tree:", right_view)

</code></pre>
</td>
<td>
<pre><code class="java">
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


</code></pre>
</td>
</tr>
</table>

## Explanation

This solution uses a Breadth-First Search (BFS) approach to traverse the binary tree level by level.

### How It Works (Step-by-Step):

1. We start by checking if the root is null. If it is, we return an empty list.

2. We initialize a queue with the root node and a result list to store the right side view.

3. We perform a level-order traversal:
  - For each level, we process all nodes in that level.
  - We keep track of the last node in each level (the rightmost node).
  - We add the value of the last node in each level to our result list.

4. While processing each node:
  - We add its left child to the queue (if it exists).
  - We add its right child to the queue (if it exists).

5. We continue this process until the queue is empty, which means we've processed all nodes.

6. Finally, we return the result list containing the right side view of the binary tree.

Let's walk through an example:
```
1
/ \
2   3
\   \
5    4
```

#### Iteration 1:
- Queue: [1]
- Process node 1, add its children 2 and 3 to the queue.
- Since 1 is the only node at this level, it is added to the result.
- Right View So Far: [1]
- Queue after processing: [2, 3]

#### Iteration 2:
- Queue: [2, 3]
- Process node 2 (add its child 5 to the queue), then process node 3 (add its child 4 to the queue).
- Node 3 is the last node at this level, so it is added to the result.
- Right View So Far: [1, 3]
- Queue after processing: [5, 4]

#### Iteration 3:
- Queue: [5, 4]
- Process node 5 (no children), then process node 4 (no children).
- Node 4 is the last node at this level, so it is added to the result.
- Right View So Far: [1, 3, 4]
- Queue after processing: [] (queue is empty, traversal ends)

Therefore, the right side view is [1, 3, 4].

## Complexity Analysis

- Time Complexity: O(n), where n is the number of nodes in the binary tree. We visit each node once.
- Space Complexity: O(d), where d is the diameter of the tree. In the worst case (for a complete binary tree), this can be up to n/2.

## Right View vs Level Order
<table>
<tr>
<th>Right View</th>
<th>Level Order</th>
</tr>
<tr>
<td>
<pre><code class="Java">
import java.util.*;

public class RightViewSolution {
public List<Integer> rightSideView(TreeNode root) {
List<Integer> rightView = new ArrayList<>();
if (root == null) return rightView;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            TreeNode rightmostNode = null;
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                
                // Keep track of the last node in this level
                rightmostNode = currentNode;
                
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
            // Add the rightmost node of this level to the result
            rightView.add(rightmostNode.val);
        }
        
        return rightView;
    }
}
</code></pre>
</td>
<td>
<pre><code class="java">
import java.util.*;

public class LevelOrderTraversalSolution {
public List<List<Integer>> levelOrder(TreeNode root) {
List<List<Integer>> levels = new ArrayList<>();
if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
            // Add the full current level to the result
            levels.add(currentLevel);
        }
        
        return levels;
    }
}

</code></pre>
</td>
</tr>
</table>

## LeetCode Link

[199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)