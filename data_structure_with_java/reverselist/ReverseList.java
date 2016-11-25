package com.lianxi.data_structure_with_java.reverselist;

/**
 * Created by tantairs on 10/28/16.
 */
public class ReverseList {

    /**
     * 遍历，将当前节点的下一个节点缓存后更改当前节点指针
     *
     */
    public static Node reverse2(Node head) {
        if (null == head) {
            return head;
        }
        Node pre = null;
        Node cur = head.getNextNode();
        Node next;
        while (null != cur) {
            next = cur.getNextNode();
            cur.setNextNode(pre);

            pre = cur;
            cur = next;
        }
        //将原链表的头节点的下一个节点置为null，再将反转后的头节点赋给head
        head.setNextNode(pre);

        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(-1);
        Node tmp = null;
        Node cur = null;
        // 构造一个长度为10的链表，保存头节点对象head
        for (int i = 1; i < 10; i++) {
            tmp = new Node(i);
            if (1 == i) {
                head.setNextNode(tmp);
            } else {
                cur.setNextNode(tmp);
            }
            cur = tmp;
        }
        //打印反转前的链表
        Node h = head;
        while (null != h) {
            if(h.getNextNode() != null){
                System.out.print(h.getRecord() + "->");
            }else {
                System.out.print(h.getRecord());
            }
            h = h.getNextNode();
        }
        //调用反转方法
        head = reverse2(head);
        System.out.println("\n*************反转之后**************");
        //打印反转后的结果
        while (null != head) {

            if(head.getNextNode() != null){
                System.out.print(head.getRecord() + "->");
            }else {
                System.out.print(head.getRecord());
            }
            head = head.getNextNode();
        }
    }

}
