package com.algorithm.tree;

/**
 * @description: 树算法题解
 * @author: liuhui
 * @Date: 2022-03-19 17:47
 **/
public class TreeAlgorithm {

    /**
     * Description:求树的最大深度
     * @Author: liuhui
     * @Date: 2022/3/19
     **/
    public class Solution104{
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 利用定义，计算左右子树的最大深度
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            // 整棵树的最大深度等于左右子树的最大深度取最大值，
            // 然后再加上根节点自己
            int res = Math.max(leftMax, rightMax) + 1;
            return res;

        }
    }

    /**
     * Description: 求最长直径（例如：左边3个子节点，右边2个，最大直径为5）
     * @Author: liuhui
     * @Date: 2022/3/19
     **/
    public class Solution543{
        int max = 0;
        public int diameterOfBinaryTree(TreeNode root) {
            maxDept(root);
            return max;
        }
        public int maxDept(TreeNode root){
            if(root == null){
                //这个地方返回0，不能返回max，因为max是全局的
                //会发生左子树修改后，右子树返回同样值的问题
                return 0;
            }
            int leftCount = maxDept(root.left);
            int rightCount = maxDept(root.right);
            //将最大深度记录到临时变量
            int myMax = leftCount+rightCount;
            //比较最大的赋值，此处注意：如果直接返回临时变量会存在少了最后一个子节点统计的问题
            max = Math.max(max,myMax);
            return Math.max(leftCount,rightCount)+1;
        }
    }

    /**
     * Description:翻转二叉树
     *              前序遍历和后序遍历都可以，区别是一个从父节点开始交换，一个从子节点开始交换
     * @Author: liuhui
     * @Date: 2022/3/19
     **/
    class Solution226 {
        TreeNode node;
        public TreeNode invertTree(TreeNode root) {
            if(root == null){
                return root;
            }
            TreeNode left = invertTree(root.left);
            TreeNode right = invertTree(root.right);
            //交换左节点跟右节点位置
            node = root.left;
            root.left = root.right;
            root.right = node;
            //返回根节点
            return root;
        }
    }


}
