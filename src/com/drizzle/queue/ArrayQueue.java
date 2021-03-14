package com.drizzle.queue;

import java.util.Scanner;

/**
 * @Description: 最基础的数组模拟队列（将数组元素放满后取出也无效）
 * @Author Drizzle
 * @Date 2021/3/14 13:09
 */
public class ArrayQueue {

    /**
     * 队列容纳的最大数量
     */
    private int maxSize;

    /**
     * 队列最前的元素下标-1（因为初始值给做-1可以和0区分开）
     */
    private int front;

    /**
     * 队列最后的元素下标-1（因为初始值给做-1可以和0区分开）
     */
    private int rear;

    /**
     * 数组
     */
    private int[] arr;

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("ܼ队列已经满了！");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue() {

        if (isEmpty()) {

            throw new RuntimeException("队列是空的！");
        }
        front++;
        return arr[front];

    }

    public void showQueue() {

        if (isEmpty()) {
            System.out.println("队列是空的！");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    public int headQueue() {

        if (isEmpty()) {
            throw new RuntimeException("队列是空的！");
        }
        return arr[front + 1];
    }

    public static void main(String[] args) {

        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show): ");
            System.out.println("e(exit): ");
            System.out.println("a(add):");
            System.out.println("g(get):");
            System.out.println("h(head):");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的值");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("弹出值%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("第一个值%d\n", res);
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
        System.out.println("已退出");
    }
}
