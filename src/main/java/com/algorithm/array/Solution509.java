package com.algorithm.array;

/**
 * @description: 斐波那契数
 * @author: liuhui
 * @Date: 2021-09-14 11:36
 **/
public class Solution509 {

    public int fib(int n) {
        int[] array = new int[n+1];
        return helper(array,n);
    }

    private int helper(int[] array, int n) {
        if(n == 0 || n == 1){
            return n;
        }
        if(array[n] != 0){
            return array[n];
        }
        array[n] = helper(array,n-1) + helper(array,n-2);
        return array[n];
    }

    public static void main(String[] args) {
        Solution509 solution509 = new Solution509();
        //
        solution509.fib(5);
    }
}
