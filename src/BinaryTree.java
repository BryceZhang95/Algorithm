import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public int key;
    public TreeNode (int key) {
        this.key = key;
    }
}
public class BinaryTree {
    List<Integer> result = new ArrayList<Integer>();
    public List<Integer> preOrder(TreeNode root) {
        // Write your solution here

        if (root == null) {
            return result;
        }
        //System.out.println(root.key);
        result.add(root.key);
        preOrder(root.left);
        preOrder(root.right);

        return result;
    }

    public boolean isBalanced(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }

        if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left),getHeight(root.right)) + 1 ;
    }

    public boolean isSymmetric(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return true;
        }



        return check(root.left,root.right);

    }

    private boolean check(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else if (left.key != right.key) {
            return false;
        }

        return check(left.left , right.right) && check(left.right , right.left);
    }

    public boolean isBST(TreeNode root) {
        // Write your solution here

        return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }

    public boolean isBST(TreeNode root, int min, int max) {

        if (root == null) {
            return true;
        }

        if (root.key <= min || root.key >= max) {
            return false;
        }

        return isBST(root.left,min,root.key) && isBST(root.right,root.key,max);

    }

    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        // Write your solution here
        if (one == null && two == null) {
            return true;
        }
        if (one == null || two == null) {
            return false;
        }
        if (one.key != two.key) {
            return false;
        }

        return isTweakedIdentical(one.left , two.left) && isTweakedIdentical(one.right , two.right)
                || isTweakedIdentical(one.left , two.right) && isTweakedIdentical(one.right, two.left);
    }
}
