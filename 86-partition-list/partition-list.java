/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // Two dummy nodes to start the two lists
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);
        
        // Pointers to the current end of the two lists
        ListNode less = lessDummy;
        ListNode greater = greaterDummy;
        
        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }
        
        // Important: end the greater list to avoid cycles
        greater.next = null;
        
        // Connect the "less" list to the "greater" list
        less.next = greaterDummy.next;
        
        return lessDummy.next;
    }
}