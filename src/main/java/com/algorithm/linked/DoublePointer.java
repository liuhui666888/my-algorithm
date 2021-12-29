package com.algorithm.linked;

/**
 * @description: 双指针链表解法集
 * @author: liuhui
 * @Date: 2021-12-13 23:03
 **/
public class DoublePointer {

    public static class ListNode {
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
            //判断两个链表大小，并生成一个新的链表
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
            //条件得是快指针
            //一个快指针一个慢指针，相遇则是有环
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

    /**
     * Description:环形链表找到环入口
     **/
    public class Solution142 {
        public ListNode detectCycle(ListNode head) {
            ListNode p1 = head,p2 = head;
            //找到环相遇的地方
            while(p2 != null && p2.next != null){
                p2 = p2.next.next;
                p1 = p1.next;
                if(p1 == p2){
                    break;
                }
            }
            //遇到空指针说明没有环
            if(p2 == null || p2.next == null){
                return null;
            }
            // 重新指向头结点
            p2 = head;
            // 快慢指针同步前进，相交点就是环起点
            while(p1 != p2){
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        }
    }

    /**
     * Description:删除倒数第N个节点
     **/
    class Solution19 {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            //两个指针一定要创建个虚拟头节点指向，
            //因为找到倒数第N个节点时，删除的是找到的节点，
            //所以 p2.next = p2.next.next; 删除的是下一个节点，
            //多一个节点，删除的刚好是第N个节点
            //因为如果直接 p2 = p2.next; 等于向前走了一个指针，而不是删除
            ListNode p1 = dummy;
            ListNode p2 = dummy;
            for(int i = 0; i < n; i++){
                p1 = p1.next;
            }
            while(p1.next != null){
                p1 = p1.next;
                p2 = p2.next;
            }
            p2.next = p2.next.next;
            return dummy.next;
        }
    }

    /**
     * Description:链表的中间节点
     **/
    class Solution876 {
        public ListNode middleNode(ListNode head) {
            ListNode p1 = head,p2 = head;
            //快慢指针，快指针走完，慢指针则为中间节点
            while(p1 != null && p1.next != null){
                p1 = p1.next.next;
                p2 = p2.next;
            }
            return p2;
        }
    }

    /**
     * Description:返回两个链表相交的节点
     **/
    class Solution160 {
        //同时走链表A和链表B，如果链表A走完了就接着走链表B，
        //反之亦然，等两个指针走到同一位置，则走到了相交节点
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p1 = headA,p2 = headB;
            while(p1 != p2){
                if(p1 == null){
                    p1 = headB;
                }else{
                    p1 = p1.next;
                }
                if(p2 == null){
                    p2 = headA;
                }else{
                    p2 = p2.next;
                }
            }
            return p1;
        }
    }

    /**
     * Description:翻转链表（双指针）
     **/
    class Solution206 {
        public ListNode reverseList(ListNode head) {
            //申请两个指针
            ListNode pre = null;
            ListNode cur = head;
            while(cur != null){
                //创建中间节点指向 cur.next ，防止链表断开
                ListNode dummy = cur.next;
                //将cur指向前一个
                cur.next = pre;
                //将前置指针向后移一步
                pre = cur;
                //将后置指针向后移一步
                cur = dummy;
            }
            return pre;
        }
    }

    /**
     * Description:是否回文字符串（两边对称）
     **/
    class Solution{
        public boolean isPalindrome(String s) {
            //为空返回true
            if (s == null){
                return true;
            }
            //创建双指针，一个从队头，一个从队尾
            int left = 0, right = s.length() - 1;
            //循环直到两个指针相交
            while (left < right) {
                //考虑空格等因素，直接前移一位（用循环是怕多个空格）
                while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                {
                    left++;
                }
                //考虑空格等因素，直接前移一位（用循环是怕多个空格）
                while (left < right && !Character.isLetterOrDigit(s.charAt(right)))
                {
                    right--;
                }
                //字符串转为小写，并判断是否相等
                //不相等则不是回文字符串
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right)))
                {
                    return false;
                }
                //指针前移
                left++;
                right--;
            }
            return true;
        }
    }

}
