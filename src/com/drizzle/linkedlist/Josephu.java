package com.drizzle.linkedlist;

/**
 * 解决约瑟夫问题
 * <p>
 * Josephu  问题为：设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 * <p>
 * 提示：用一个不带头结点的循环链表来处理Josephu 问题：先构成一个有n个结点的单循环链表，然后由k结点起从1开始计数，计到m时，对应结点从链表中删除，然后再从被删除结点的下一个结点又从1开始计数，直到最后一个结点从链表中删除算法结束。
 */
public class Josephu {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(10, 20);
    }
}


class CircleSingleLinkedList {

    // 第一个小孩
    private Boy first = null;


    /**
     * 生成一个单向循环链表
     *
     * @param nums
     */
    public void addBoy(int nums) {

        if (nums < 1) {
            System.out.println("nums值不正确");
            return;
        }

        // 指向最后一个小孩
        Boy curBoy = null;

        for (int i = 1; i <= nums; i++) {

            Boy boy = new Boy(i);

            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                // 新添加孩子节点时
                // 将孩子节点设置到最后一个孩子节点后面
                // 然后将新孩子节点的下一个节点设置为第一个节点
                // 然后将新孩子节点设置为最后一个孩子
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy() {

        if (first == null) {
            System.out.println("没有任何小孩~~");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }


    /**
     * 从第startNo开始数数，每countNum出列一个
     *
     * @param startNo
     * @param countNum
     */
    public void countBoy(int startNo, int countNum) {

        if (first == null || startNo < 1) {
            System.out.println("参数有误，请重新输入");
            return;
        }

        // 最后一个小孩，该辅助节点的作用：helper节点时当前节点的前一个节点，把当前节点出圈时，需要使用上一个节点把当前节点跳过去
        // 且可作为出递归的条件，即循环队列中仅剩下一个节点（下一个节点等于自身）
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        // 如果计数不是从第一个开始计数的，则需要将first移动位置，同样的helper需要跟着移动
        for (int j = 0; j < startNo - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        while (true) {
            if (first.getNext() == first) {
                break;
            }

            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }

            System.out.printf("小孩%d出圈\n", first.getNo());

            first = first.getNext();
            helper.setNext(first);

        }
        System.out.printf("最后留在圈中的小孩的编号：%d \n", first.getNo());
    }
}


class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
