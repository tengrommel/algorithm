package com.teng.algorithm.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        // 测试一把
        // 创建一个队列
        CircleArray arrayQueue = new CircleArray(4);
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}

class CircleArray {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数据用于存放数据，模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满
    public boolean isFull() {
        return (rear+1) % maxSize == front;
    }
    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    // 添加数据
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满不能添加数据");
            return;
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    // 取出数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        // 这里需要分析出front是指向队列的第一个元素
        // 1、先把front对应的值保留到一个临时变量
        // 2、将front后移
        // 3、将临时保存的变量返回
        int value = arr[front];
        front = (front+1) % maxSize;
        return value;
    }
    // 显示队列
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~~");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        for (int i = front; i <front+size() ; i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }
    // 求出当前队列有效数据的个数
    public int size() {
        return (rear+maxSize-front)%maxSize;
    }
    // 打印头元素
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("列表为空，没有数据~~~");
        }
        return arr[front];
    }
}