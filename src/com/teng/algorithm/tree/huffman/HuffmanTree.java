package com.teng.algorithm.tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    // 创建结点类
    // 为了让Node对象集合支持排序
    // 让Node实现Comparable接口
    static class Node implements Comparable<Node> {
        int value;// 结点权值
        Node left; // 指向左子节点
        Node right; // 指向右子节点

        // 写一个前序遍历
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    // 创建huffman树

    // 生成赫夫曼对应的赫夫曼编码

    /**
     * 需要创建成赫夫曼树的方法
     * @param arr
     * @return
     */
    public static Node createHuffman(int[] arr) {
        // 第一步为了操作方便
        // 1、遍历arr数组
        // 2、将arr的每个元素构成成一个Node
        // 3、将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        // 我们处理的过程是一个循环的过程

        while(nodes.size() > 1) {
            // 排序从小到大
            Collections.sort(nodes);
            System.out.println("nodes = " + nodes);
            // 取出根节点权值最小的两颗二叉树
            // (1)取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            // (2)取出第二小的节点
            Node rightNode = nodes.get(1);
            //（3）构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.right = rightNode;
            parent.left = leftNode;
            // （4）从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // (5)将parent加入到nodes
            nodes.add(parent);

            System.out.println("第一次处理后" + nodes);
        }
        return nodes.get(0);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树，不能遍历~~~");
        }
    }

    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29,6, 1};
        Node root = createHuffman(arr);
        preOrder(root);
    }


}
