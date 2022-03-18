package com.algorithm.tree;

import com.algorithm.linked.ListNode;

/**
 * @description: 遍历二叉树
 * 前中后序遍历解析
 * @author: liuhui
 * @Date: 2022-03-18 23:35
 **/
public class ErgodicTree {

    /**
     * Description: 二叉树遍历函数
     *    前序遍历是指每棵树先遍历根节点，再遍历左节点，最后遍历右节点
     *    中序遍历是指每棵树先遍历左节点，再遍历根节点，最后遍历右节点
     *    后序遍历是指每棵树先遍历左节点，再遍历右节点，最后遍历根节点
     *    维度是以根节点在前中后的遍历顺序为准
     * @param
     * @return: void
     * @Author: liuhui
     * @Date: 2022/3/18
     **/
    public void traverse(TreeNode root){
        //以下是二叉树遍历过程，每个位置代表在哪个位置操作为什么遍历
        //除了位置的不同，还有时间节点的不同
        //前序位置的代码在刚刚进入一个二叉树节点的时候执行
        //后序位置的代码在将要离开一个二叉树节点的时候执行
        //中序位置的代码在一个二叉树节点左子树都遍历完，即将开始遍历右子树的时候执行

        //前序位置
        System.out.println(root.value);
        traverse(root.left);
        //中序位置
        System.out.println(root.value);
        traverse(root.right);
        //后序位置
        System.out.println(root.value);
    }

    /**
     * Description: 倒叙打印单链表（递归）
     * @param node
     * @return: void
     * @Author: liuhui
     * @Date: 2022/3/18
     **/
    public void traverse(ListNode node){
        if(node == null){
            return;
        }
        traverse(node.next);
        //后序位置
        //后序位置输出，代表时间上全执行完输出，也就是说为倒序输出
        System.out.println(node.val);
    }

}
