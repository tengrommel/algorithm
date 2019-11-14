package com.teng.algorithm.queue;

public class ArrayQueueDemo {
    public static void main(String[] args) {

    }
}
// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 该数据用于存放数据，模拟队列

    //创建队列的构造器

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部，并不包含（前一个位置）
        rear = -1; // 指向队列尾，指向队列尾的数据（即就是队列最后一个数据）
    }
    // 判断队列是否满
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满不能加入数据");
        }
        rear++;// 让rear后移
        arr[rear] = n;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            // 通过抛出异常来处理
            throw new RuntimeException("队列为空，不能取数据");
        }
        front++; // front后移
        return arr[front];
    }
    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~~");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        // 判断是否为空
        if (isEmpty()) {
            System.out.println("对列为空，没有数据~~~");
            throw new RuntimeException("队列为空，没有数据~~");
        }
        return arr[front+1];
    }
}
