package com.teng.algorithm.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        // 说明 我们先手动创建二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        // 测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;
    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // z中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 前序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个节点，这里要立即判断root是不是要删除的节点
            if (root.getNo() == no) {
                root = null;
            }
        } else {
            System.out.println("空树不能删除");
        }
    }
}

// 先创建HeroNode节点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        // 左子树递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    // 编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);// 先输出父节点
        // 递归向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    // 后序遍历
    public void postOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.postOrder();
        }
        // 输出父节点
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        // 比较当前节点
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明我们左子树找到了
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    // 前序遍历查找
    public HeroNode infixSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明我们左子树找到了
            return resNode;
        }
        // 比较当前节点
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    // 前序遍历查找
    public HeroNode postSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明我们左子树找到了
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        if (resNode != null) {
            // 说明我们左子树找到了
            return resNode;
        }
        // 比较当前节点
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
