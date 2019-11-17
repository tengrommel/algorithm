package com.teng.algorithm.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 创建一个ArrBinaryTree
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr; // 存储数据节点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }
    // 编写一个方法，完成顺序存储二叉树的前序遍历

    /**
     * index 数组的下标
     * @param index
     */
    public void preOrder(int index) {
        // 如果数组为空，或者arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.print(arr[index]+" ");
        if ((index*2 + 1) < arr.length){
            preOrder(2*index + 1);
        }
        // 向右递归遍历
        if((index*2 + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    // 打印
    public void preOrder() {
        this.preOrder(0);
    }
}
