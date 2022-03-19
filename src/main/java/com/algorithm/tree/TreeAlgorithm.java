package com.algorithm.tree;

/**
 * @description: 树算法题解
 * @author: liuhui
 * @Date: 2022-03-19 17:47
 **/
public class TreeAlgorithm {

    /**
     * Description:求树的最大深度
     * @param root
     * @return: int
     * @Author: liuhui
     * @Date: 2022/3/19
     **/
    public int Solution104(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 利用定义，计算左右子树的最大深度
        int leftMax = Solution104(root.left);
        int rightMax = Solution104(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        int res = Math.max(leftMax, rightMax) + 1;
        return res;

    }


}
