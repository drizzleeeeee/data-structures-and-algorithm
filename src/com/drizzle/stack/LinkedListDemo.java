package com.drizzle.stack;

import java.util.Scanner;

/**
 * 使用链表模拟栈。
 * 定义一个node节点，
 * 入栈：第一个入栈节点放到该节点后，后续入栈节点也放到该节点后，然后已有节点放到新节点后
 * 出栈：将node节点后的一个节点出栈
 * 栈满：curSize == maxSize
 * 栈空：curSize == 0
 */
public class LinkedListDemo {

    public static void main(String[] args) {

        LinkedListStack stack = new LinkedListStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show:");
            System.out.println("exit:");
            System.out.println("push:");
            System.out.println("pop: ");
            System.out.println("");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("%d\n", res);
                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("退出~~~");
    }
}

class LinkedListStack {
    private int maxSize;
    private Node node;
    private int curSize;

    public LinkedListStack(int maxSize) {
        this.maxSize = maxSize;
        node = new Node(-1);
    }

    public boolean isFull() {
        return curSize == maxSize;
    }

    public boolean isEmpty() {
        return curSize == 0;
    }

    public void push(int value) {

        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        curSize++;
        if (node.getNext() == null) {
            node.setNext(new Node(value));
            return;
        }

        Node newNode = new Node(value);
        newNode.setNext(node.getNext());
        node.setNext(newNode);
    }

    public int pop() {

        if (isEmpty()) {

            throw new RuntimeException("栈空~");
        }
        int value = node.getNext().getNo();
        curSize--;
        return value;
    }

    public void list() {
        if (isEmpty()) {
            System.out.println("栈空~~");
            return;
        }

        Node temp = node.getNext();
        for (int i = curSize; i > 0; i--) {
            int no = temp.getNo();
            temp = temp.getNext();
            System.out.printf("stack[%d]=%d\n", i, no);
        }
    }

    public static class Node {
        private int no;
        private Node next;

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    '}';
        }
    }
}
