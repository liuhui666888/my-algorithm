package com.algorithm.linked;

/**
 * @description: 双指针链表解法集
 * @author: liuhui
 * @Date: 2021-12-13 23:03
 **/
public class DoublePointer {

    static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Description:合并两个有序链表
     **/
    class Solution21 {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 虚拟头结点
            ListNode dummy = new ListNode(-1), p = dummy;
            ListNode p1 = list1,p2 = list2;
            while(p1 != null && p2 != null){
                if(p1.val < p2.val){
                    p.next = p1;
                    p1 = p1.next;
                }else{
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            if (p1 != null) {
                p.next = p1;
            }

            if (p2 != null) {
                p.next = p2;
            }
            return dummy.next;
        }
    }

    /**
     * Description:环形链表
     **/
    class Solution141 {
        public boolean hasCycle(ListNode head) {
            ListNode p1 = head,p2 = head;
            //条件要是快指针
            while(p1 != null && p1.next != null){
                p1 = p1.next.next;
                p2 = p2.next;
                if(p1 == p2){
                    return true;
                }
            }
            return false;
        }
    }

}
