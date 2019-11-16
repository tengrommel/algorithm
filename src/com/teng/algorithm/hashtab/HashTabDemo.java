package com.teng.algorithm.hashtab;

import java.util.Scanner;

public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("exit: 退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 创建HashTab管理多条链表
class HashTab {
    private int size;
    private EmpLinkedList[] empLinkedListArray;
    // 构造器
    public HashTab(int size) {
        this.size = size;
        // 初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // ？留一个坑，这时不要忘了分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }
    // 添加
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }
    // 遍历所有的链表，遍历hashtab
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            // 找到
            System.out.printf("在第%d条链表中找到了雇员id = %d", empLinkedListNO, id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    // 编写一个散列函数，使用一个简单取模法
    public int hashFun(int id) {
        return id%size;
    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

// 创建EmpLinkedList，表示链表
class EmpLinkedList {
    // 头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head; // 默认null
    // 添加雇员到链表
    // 说明
    // 1、假定当添加雇员时，id是自增的，即id的分配总是从小到大
    // 因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助的指针，帮忙定义到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                // 说明到链表最后
                break;
            }
            curEmp = curEmp.next;// 后移
        }
        //退出时直接将emp 加入链表
        curEmp.next = emp;
    }
    // 遍历链表的雇员信息
    public void list(int no) {
        if (head==null) {
            System.out.println("链表为空" + (no+1) + "链表为空");
            return;
        }
        System.out.println("第" + (no+1) + "链表的信息为");
        Emp curEmp = head; // 辅助指针
        while (true) {
            System.out.printf("=> id =%d name=%s\n", curEmp.id, curEmp.name);
            if (curEmp.next == null) {
                // 说明curEmp已经是最后节点
                break;
            }
            curEmp = curEmp.next; // 后移遍历
        }
    }
    // 根据id查找雇员
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while (true) {
            if (curEmp.id == id) {
                break; // 这时curEmp就指向要查找的雇员
            }
            // 退出条件
            if (curEmp.next == null) {
                // 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}