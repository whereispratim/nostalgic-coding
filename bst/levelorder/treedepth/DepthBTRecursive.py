class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class DepthBTRecursive:
    def maxDepth(self, root: TreeNode) -> int:
        # Base case: if the node is null, the depth is 0
        if not root:
            return 0
        # Recursively find the depth of left and right subtrees and return the max depth + 1
        return max(self.maxDepth(root.left), self.maxDepth(root.right)) + 1

# Example usage:
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
