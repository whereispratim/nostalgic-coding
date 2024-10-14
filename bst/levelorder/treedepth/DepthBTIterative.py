from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class DepthBTIterative:
    def maxDepth(self, root: TreeNode) -> int:
        if not root:
            return 0

        queue = deque([root])
        depth = 0

        # Perform BFS
        while queue:
            depth += 1
            level_size = len(queue)  # Number of nodes at the current level
            for _ in range(level_size):
                current = queue.popleft()
                # Add the child nodes for the next level
                if current.left:
                    queue.append(current.left)
                if current.right:
                    queue.append(current.right)

        return depth

# Example usage:
if __name__ == "__main__":
    solution = DepthBTIterative()

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
