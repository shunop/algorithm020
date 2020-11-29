package nop.Week02;

import java.util.ArrayList;
import java.util.List;

//给定一个二叉树的根节点 root ，返回它的 中序 遍历。
public class L094M_binary_tree_inorder_traversal {
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return list;
        if (root.left != null ) {
            inorderTraversal(root.left);
        }
        list.add(root.val);
        if (root.right != null ) {
            inorderTraversal(root.right);
        }
        return list;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
     this.val = val;
     this.left = left;
     this.right = right;
    }
}