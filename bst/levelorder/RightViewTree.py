from collections import deque

# Definition for a binary tree node.
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

# Example Usage:
if __name__ == "__main__":
    # Construct the binary tree
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
