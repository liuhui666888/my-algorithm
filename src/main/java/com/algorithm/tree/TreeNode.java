package com.algorithm.tree;

/**
 * @description: 二叉树
 * @author: liuhui
 * @Date: 2022-03-18 23:31
 **/
public class TreeNode {

    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    public TreeNode(int value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
