//[每日一题-day04]
package nop.Week02;

/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
public class L024M_swap_nodes_in_pairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode prefix = new ListNode(0, head);
        help(prefix);
        return prefix.next;
    }
    public static void help(ListNode prefix) {
        if (prefix == null) return;
        ListNode one = prefix.next;
        if (one != null) {
            ListNode two = one.next;
            if (two != null) {
                ListNode nextGroup = two.next;
                //swap
                prefix.next = two;
                two.next = one;
                one.next = nextGroup;
                help(one);
            }
        }
    }
}


