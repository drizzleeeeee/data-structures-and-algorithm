package com.drizzle.queue;

import java.util.Scanner;

/**
 * @Description: 循环数组模拟队列（真正可用的队列）使用取模队列大小做到循环的效果
 * @Author Drizzle
 * @Date 2021/3/14 13:09
 */
public class CircleArrayQueue {

    /**
     * 队列容纳的最大数量
     */
    private int maxSize;

    /**
     * 队列最前的元素下标-1（因为初始值给做-1可以和0区分开）
     */
    private int front;

    /**
     * 队列最后的元素下标（因为初始队列没有元素，初始值给做-1）
     */
    private int rear;

    /**
     * 数组
     */
    private int[] arr;

    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull() {

        // 当队列尾 减去 队列头 +1 = 队列长度时，队列满。 front表示的是队列头的前一个，所以front+1表示的是队列头
        return (rear - (front + 1)) % maxSize + 1 == maxSize;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {

        if (isFull()) {
            System.out.println("ܼ队列已经满了！");
            return;
        }
        rear = rear + 1;
        arr[rear % maxSize] = n;
    }

    public int getQueue() {

        if (isEmpty()) {

            throw new RuntimeException("队列是空的！");
        }
        front = front + 1;
        int val = arr[front % maxSize];
        arr[front % maxSize] = 0;
        return val;
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
        return arr[(front + 1) % maxSize];
    }

    public static void main(String[] args) {

        CircleArrayQueue queue = new CircleArrayQueue(3);
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
