package com.teng.algorithm.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊椅", "玉麒麟");
        HeroNode newHeroNode = new HeroNode(2, "小", "欧克");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();
        // 删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.list();
    }
}

// 定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    //先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");
    // 添加节点到单项链表
    // 当不考虑编号顺序时
    // 找到当前链表的最后节点
    // 将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head 节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
    }
    // 修改节点的信息，根据no编号来修改，即no编号不能改
    // 1 根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到了该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到了
                flag=true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            // 没有找到
            System.out.printf("没有找到编号为%d 的节点", newHeroNode.no);
        }
    }

    // 显示链表【遍历】
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动 我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移，不后移死循环
            temp = temp.next;
        }
    }

    public void addByOrder(HeroNode heroNode) {
        // 因为单链表，因为我们找的temp是位于添加位置
        HeroNode temp = head;
        boolean flag = false; // 标识
        //添加的编号是否存在默认为false
        while (true) {
            if (temp.next == null) {
                // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {
                // 位置找到了
                break;
            }
            if (temp.next.no == heroNode.no) {
                // 说明希望添加的node已经存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移， 遍历链表
        }
        // 判断flag的值
        if (flag) {
        // 说明编号已经存在
            System.out.printf("准备插入的英雄的编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 删除节点
    // 说明我们在比较时，是temp.next.no和需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;// 标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                // 找到的待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 找到
            temp.next = temp.next.next;
        }else {
            System.out.println("要删除的%d 节点不存在\n");
        }
    }
}

// 定义一个HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;// 指向下一个节点

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // 为了显示方法，我们重新toString

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
