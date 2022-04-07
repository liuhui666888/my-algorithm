package com.algorithm.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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

    /**
     * Description: 从前序与中序遍历序列构造二叉树
     * @Author: liuhui
     * @Date: 2022/3/21
     **/
    class Solution105 {
        //传入一个前序数组与一个中序数组
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //传入前序数组及其最小下标最大下标，中序数组最小下标最大下标
            return build(preorder,0,preorder.length-1,
                    inorder,0,preorder.length-1);
        }

        public TreeNode build(int[] preorder,int preStar,int preEnd,
                              int[] inorder,int inStar,int inEnd){
            if(preStar > preEnd || inStar > inEnd){
                return null;
            }
            //前序的第一个节点为根节点
            int root = preorder[preStar];
            int index=0;
            //循环找到根节点在中序数组中的下标位置
            for(int i = 0; i <= inEnd; i++){
                if(inorder[i] == root){
                    index = i;
                }
            }
            //创建根节点
            TreeNode rootNode = new TreeNode(root);
            //从中序数组中找出左子树的size
            int leftSize = index - inStar;
            //前序数组的左节点的位置，和中序的左节点的位置集为左子树
            rootNode.left = build(preorder,preStar+1,preStar+leftSize,
                    inorder,inStar,index-1);
            //前序数组的右节点的位置，和中序的右节点的位置集为右子树
            rootNode.right = build(preorder,preStar + leftSize+1,preEnd,
                    inorder,index+1,inEnd);
            return rootNode;
        }
    }


    /**
     * Description: 从中序与后序遍历序列构造二叉树
     * @Author: liuhui
     * @Date: 2022/3/21
     **/
    class Solution106 {
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return build(inorder,0,inorder.length-1,
                    postorder,0,postorder.length-1);
        }

        public TreeNode build(int[] inorder,int inStar,int inEnd,
                              int[] postorder,int poStar,int poEnd){
            if(inStar > inEnd || poStar > poEnd){
                return null;
            }
            int root = postorder[poEnd];
            TreeNode rootNode = new TreeNode(root);
            int index = 0;
            //此处注意，是大于等于，不然最后一个算不到
            for(int i = 0; i <= inEnd; i++){
                if(inorder[i] == root){
                    index = i;
                    break;
                }
            }
            int leftSize = index - inStar;
            rootNode.left = build(inorder,inStar,index-1,
                    postorder,poStar,leftSize+poStar-1);
            //此处注意，因为每次postar都会移动，所以要算上移动的下标
            rootNode.right = build(inorder,index+1,inEnd,
                    postorder,leftSize+poStar,poEnd-1);
            return rootNode;
        }
    }


    /**
     * Description: 根据前序和后序遍历构造二叉树
     *          思路：前序遍历的第二个节点也就是左子树的根节点
     *                 对应后序遍历的左子树的最后一个节点
     *                 也就能找到左子树的size了
     * @Author: liuhui
     * @Date: 2022/3/26
     **/
    class Solution889 {
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            return handel(preorder,0,preorder.length - 1,
                    postorder,0,postorder.length -1 );
        }

        public TreeNode handel(int[] preorder,int preStar,int preEnd,
                               int[] postorder,int poStar,int poEnd){
            if(preStar > preEnd){
                return null;
            }

            if(preStar == preEnd){
                return new TreeNode(preorder[preStar]);
            }
            int root = preorder[preStar];
            int leftRoot = preorder[preStar + 1];
            int index = 0;
            for(int i = poStar ; i < poEnd;i++){
                if(leftRoot == postorder[i]){
                    index = i;
                    break;
                }
            }
            int leftSize = index - poStar +1;
            TreeNode rootNode = new TreeNode(root);
            rootNode.left = handel(preorder,preStar+1,preStar+leftSize,
                    postorder,poStar,index);
            rootNode.right = handel(preorder,leftSize+preStar+1,preEnd,
                    postorder,index+1,poEnd-1);
            return rootNode;
        }
    }


    /**
     * Description: 二叉树的序列化与反序列化
     * @Author: liuhui
     * @Date: 2022/3/28
     **/
    public class Solution297 {

        String flag = "#";

        // 序列化为一个string
        public String serialize(TreeNode root) {
            StringBuilder strBuilder = new StringBuilder();
            serializeHandel(root,strBuilder);
            return strBuilder.toString();
        }

        // 反序列化为一棵树
        public TreeNode deserialize(String data) {
            LinkedList<String> link = new LinkedList();
            String[] strArr =  data.split(",");
            for(String str : strArr){
                link.addLast(str);
            }
            return deserializeHandel(link);
        }

        //序列化处理器
        void serializeHandel(TreeNode root,StringBuilder strBuilder){
            //如果为空，打个标识记录
            if(root == null){
                strBuilder.append(flag).append(",");
                return;
            }
            //不为空将参数追加进去
            strBuilder.append(root.value).append(",");
            serializeHandel(root.left,strBuilder);
            serializeHandel(root.right,strBuilder);
        }

        //反序列化处理器
        TreeNode deserializeHandel(LinkedList<String> link){
            //反序列化用LinkedList处理
            String first = link.removeFirst();
            //为打标记得则为空
            if(first.equals(flag)){
                return null;
            }
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = deserializeHandel(link);
            root.right = deserializeHandel(link);
            return root;
        }

    }


    /**
     * Description: 寻找重复的子树
     * @Author: liuhui
     * @Date: 2022/3/29
     **/
    class Solution652 {

        String flag = "#";
        HashMap<String,Integer> map = new HashMap();
        List<TreeNode> list = new ArrayList<TreeNode>();

        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            handle(root);
            return list;
        }

        String handle(TreeNode root){
            //为空的话加个标识
            if(root == null){
                return flag;
            }
            //分别返回左子树和右子树的序列化
            String left = handle(root.left);
            String right = handle(root.right);
            String str = root.value+","+left+","+right;
            Integer mapValue =  map.get(str);
            //value为空代表暂时无重复的子树，则加入当前子树的序列化
            if(mapValue == null){
                map.put(str,1);
                return str;
            }
            //这里判断1，为了避免重复加入
            if(mapValue == 1){
                map.put(str,mapValue+1);
                //有重复子树，则在集合里添加这个子树
                list.add(root);
            }
            return str;
        }
    }


    /**
     * Description: 归并排序
     * @Author: liuhui
     * @Date: 2022/4/7
     **/
    class Solution912 {
        int[] temp;
        public int[] sortArray(int[] nums) {
            //创建一个辅助数组
            temp = new int[nums.length];
            sort(nums,0,nums.length-1);
            return nums;
        }

        void sort(int[] nums,int star,int end){
            if(star == end){
                return;
            }
            // 考虑左右树，则下标end-下标star为左半边或右半边的真实长度
            // 用真实长度/2,则为真实长度的一半，再加上stat起始下标，则为左子树或右子树的中点
            int index = star + (end - star) / 2;
            //左半边为index，右半边起始为index+1，不可颠倒
            sort(nums,star,index);
            sort(nums,index+1,end);
            //拆分后合并
            merge(nums,star,index,end);
        }

        void merge(int[] nums, int star, int index, int end) {
            // 先把 nums[lo..hi] 复制到辅助数组中
            // 以便合并后的结果能够直接存入 nums
            for (int i = star; i <= end; i++) {
                temp[i] = nums[i];
            }

            // 数组双指针技巧，合并两个有序数组
            int a = star, j = index + 1;
            for (int i = star; i <= end; i++) {
                // 左半边数组已全部被合并，则将右边数组的值进行赋值
                if (a == index + 1) {
                    nums[i] = temp[j];
                    j++;
                }
                // 右半边数组已全部被合并，则将左边数组的值进行赋值
                else if (j == end + 1) {
                    nums[i] = temp[a];
                    a++;
                }
                // 左边数组的值大于右边数组的值，则将右边数组的值进行赋值
                else if (temp[a] > temp[j]) {
                    nums[i] = temp[j];
                    j++;
                }
                // 左边值大，则直接将左边边数组的值进行赋值
                else {
                    nums[i] = temp[a];
                    a++;
                }
            }
        }
    }


}
