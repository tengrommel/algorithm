package com.teng.algorithm.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊椅", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "人云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();
        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}

// 创建一个双向链表的类
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");
    // 返回头节点
    public HeroNode2 getHead() {
        return head;
    }
    // 遍历双向链表的方法
    // 显示链表【遍历】
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动 我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    // 添加一个节点到双向链表最后
    public void add(HeroNode2 heroNode) {
        //因为head 节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    // 修改一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
    public void update(HeroNode2 newHeroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 先定义一个辅助变量
        HeroNode2 temp = head.next;
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

    // 删除节点
    public void del(int no) {
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null){ // 已经找到链表的最后
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 可以删除
            temp.pre.next = temp.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;// 指向下一个节点
    public HeroNode2 pre;// 指向前一个节点

    // 构造器
    public HeroNode2(int no, String name, String nickname) {
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
