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

    /**
     * Description: 二叉树展开为链表
     * @Author: liuhui
     * @Date: 2022/3/20
     **/
    class Solution114 {
        public void flatten(TreeNode root) {

            if (root == null){
                return;
            }
            flatten(root.left);
            flatten(root.right);
            TreeNode left = root.left;
            TreeNode right = root.right;

            // 2、将左子树作为右子树
            root.left = null;
            root.right = left;

            //创建临时变量指向root节点
            TreeNode p = root;
            //此时当前的右子树为左子树移过来的
            //将原先的右子树接到当前右子树的末端
            //因为原先的左子树节点可能是多个，所以要循环到末端再指向
            while (p.right != null) {
                p = p.right;
            }
            p.right = right;
        }
    }

    /**
     * Description: 填充每个节点的下一个右侧节点指针
     * @Author: liuhui
     * @Date: 2022/3/20
     **/
    class Solution116 {
        //函数
        public Node connect(Node root) {
            if(root == null){
                return root;
            }
            connect2(root.left,root.right);
            return root;
        }

        //辅助函数
        public void connect2(Node leftNode,Node rightNode){
            if(leftNode == null || rightNode == null){
                return;
            }
            //将左节点的next指向右节点
            leftNode.next = rightNode;
            //左子树指向
            connect2(leftNode.left,leftNode.right);
            //右子树指向
            connect2(rightNode.left,rightNode.right);
            //将左子树的右节点指向右子树的左节点
            connect2(leftNode.right,rightNode.left);
        }
    }


    /**
     * Description: 最大二叉树
     * @Author: liuhui
     * @Date: 2022/3/20
     **/
    class Solution654 {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            //传入数组，及最小下标，最大下标
            return build(nums,0,nums.length-1);
        }

        //辅助函数
        TreeNode build(int[] nums,int lo,int hi){
            //最小下标大于最大下标则返回。不能写等于，因为会导致只有一个参数时无法处理
            if(lo > hi){
                return null;
            }
            int index = 0;
            //此处value要写-1，避免有值为0的情况，比较结果错误
            int value = -1;
            //循环找出最大值及下标
            for(int i = lo;i <= hi; i++){
                if(nums[i] > value){
                    value = nums[i];
                    index = i;
                }
            }
            //将最大值看做root节点
            TreeNode root = new TreeNode(value);
            //将返回的值指向左右子节点
            root.left = build(nums,lo,index-1);
            root.right = build(nums,index+1,hi);
            return root;
        }
    }


}
