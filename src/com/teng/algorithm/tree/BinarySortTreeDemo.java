package com.teng.algorithm.tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环得添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉树");
    }
}

// 创建二叉排序树
class BinarySortTree {
    private Node root;
    // 添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;// 如果root为空直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1、需求先去找到要删除的节点 targetNode
            Node targetNode = search(value);
            // 如果没有找到要删除的节点
            if (targetNode == null) {
                return;
            }
            // 如果我们发现targetNode没有父节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            // 去找到targetNode的父节点
            Node parent = searchParent(value);
            // 如果要删除的节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断targetNode是父节点的左子节点，还是右子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    // 是右子节点
                    parent.right = null;
                }
            }
        }
    }

    // 终须遍历
    public void infixOrder() {
        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉排序为空");
        }
    }
}

// 节点
class Node {
    int value;
    Node left;
    Node right;

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    // 查找要删除的节点

    /**
     *
     * @param value 希望删除的节点的值
     * @return 如果找到返回该节点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            // 如果查找的值小于当前节点，向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            // 如果查找的值不小于当前的节点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除节点的父结点

    /**
     *
     * @param value 要找到的节点的值
     * @return 返回的是要删除的父节点，如果没有就返回null
     */
    public  Node searchParent(int value) {
        // 如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有父节点
            }
        }
    }

    // 添加节点的方法
    // 递归的方式添加节点，注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入的节点的值，和当前子树的根节点的关系
        if (node.value < this.value) {
            // 如果当前节点左子节点为空
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归向左子树添加
                this.left.add(node);
            }
        } else { // 添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            }else {
                // 递归向右子树添加
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
