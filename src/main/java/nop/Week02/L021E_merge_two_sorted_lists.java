package nop.Week02;

/*
将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
示例：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
//Definition for singly-linked list.
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
 */

public class L021E_merge_two_sorted_lists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        //迭代
        ListNode preResult = null;
        ListNode curr = null;
        if (l1.val > l2.val) {
            preResult = l2;
            curr = l2;
            l2 = l2.next;
        } else {
            preResult = l1;
            curr = l1;
            l1 = l1.next;
        }
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                curr.next = l2;
                break;
            }
            if (l2 == null) {
                curr.next = l1;
                break;
            }
            if (l1.val > l2.val) {
                curr.next = l2;
                curr = curr.next;
                l2 = l2.next;
            } else {
                curr.next = l1;
                curr = curr.next;
                l1 = l1.next;
            }
        }
        return preResult;
    }

    public ListNode mergeTwoLists_2(ListNode l1, ListNode l2) {
        //这才是迭代的精髓
        ListNode profix = new ListNode(-1);
        ListNode curr = profix;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 == null) curr.next = l2;
        else curr.next = l1;
        return profix.next;
    }

    public ListNode mergeTwoLists_3(ListNode l1, ListNode l2) {
        //递归
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists_3(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists_3(l2.next, l1);
            return l2;
        }
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(4);

        ListNode listNode2 = new ListNode(1);
        listNode2.next = new ListNode(3);
        listNode2.next.next = new ListNode(4);

        ListNode result = mergeTwoLists(listNode, listNode2);
        print(result);
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}


