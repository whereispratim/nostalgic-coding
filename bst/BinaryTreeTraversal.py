from collections import deque

class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class BinaryTreeTraversal:
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

# Example usage
root = TreeNode(50)
root.left = TreeNode(30)
root.right = TreeNode(70)
root.left.left = TreeNode(20)
root.left.right = TreeNode(40)
root.right.left = TreeNode(60)
root.right.right = TreeNode(80)

solution = BinaryTreeTraversal()
print("Inorder traversal:", solution.inorderTraversal(root))
print("Preorder traversal:", solution.preorderTraversal(root))
print("Postorder traversal:", solution.postorderTraversal(root))
print("Level order traversal:", solution.levelOrder(root))