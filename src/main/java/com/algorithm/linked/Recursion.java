package com.algorithm.linked;

/**
 * @description: 链表递归集
 * @author: liuhui
 * @Date: 2021-12-17 11:20
 **/
public class Recursion {

    /**
     * Description:翻转链表（递归）
     **/
    class Solution206 {
        public DoublePointer.ListNode reverseList(DoublePointer.ListNode head) {
            //如果head为空或者head.next为空则返回head
            if(head == null || head.next == null){
                return head;
            }
            //返回后 pre = head.next
            //      head.next.next = pre.next
            DoublePointer.ListNode pre = reverseList(head.next);
            //这一步为翻转
            //即 让 pre 指向 head
            //也就是指向前一个
            head.next.next = head;
            //已经指向了前一个
            //所以要把原来的指向断开
            //防止循环指向
            head.next = null;
            //
            return pre;
        }
    }

}
