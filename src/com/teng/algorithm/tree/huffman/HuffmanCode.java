package com.teng.algorithm.tree.huffman;

import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); // 40
        getNodes(contentBytes);
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTreeRoot);
        getCodes(huffmanTreeRoot, "", stringBuilder);
        System.out.println("生成的哈夫曼编码表" + huffmanCode);
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 思路
    // 1、将赫夫曼编码表存放在Map<Byte, String>形式
    static Map<Byte, String> huffmanCode = new HashMap<Byte, String>();
    // 2、在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node 传入结点
     * @param code 路径：左子节点是0，右子节点是1
     * @param stringBuilder 是用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            // 如果node == null 不处理
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                // 非叶子节点
                // 递归处理
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node, "1", stringBuilder2);
            } else { // 说明是一个叶子节点
                // 就表示找到了叶子节点的最后
                huffmanCode.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 前序遍历的方法
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树，它的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树加入到nodes
            nodes.add(parent);
        }
        // nodes最后的结点，就是huffman的根节点
        return nodes.get(0);
    }

    /**
     *
     * @param bytes 接收字节数组
     * @return 返回的List形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 1创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();
        // 遍历bytes，统计每一个byte出现的次数->map[key, value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                // Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每一个键值对转成一个Node对象，并加入到nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

//  创建Node，待数据和权值
class Node implements Comparable<Node>{
    Byte data; // 存放数据本身，比如'a' => 97, ' ' => 32
    int weight; // 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
