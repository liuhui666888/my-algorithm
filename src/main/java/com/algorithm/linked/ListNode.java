package com.algorithm.linked;

import lombok.Data;

/**
 * @description: 链表
 * @author: liuhui
 * @Date: 2022-03-18 23:21
 **/
@Data
public class ListNode {


    public int val;
    public ListNode next;
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
