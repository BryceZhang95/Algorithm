class ListNode{
    public int value;
    public ListNode next;
    public ListNode(int value){
        this.value = value;
        next = null;
    }
}
public class LinkedList {

    public ListNode reverseIterative(ListNode head){
        ListNode prev = null;
        while (head!=null){
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    public ListNode reverseRecursion(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode swapPairs(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs(temp.next);
        temp.next = head;
        return temp;
    }

    public ListNode middleNode(ListNode head){
        // corner case
        if (head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        // if the middle node of N1 -> N2 -> N3 -> N4 is N2
        while (fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
//       // if the middle node of N1 -> N2 -> N3 -> N4 is N3
//        while (fast!=null && fast.next!=null){
//            slow = slow.next;
//            fast = fast.next.next;
//        }
        return slow;
    }

    public boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }

        }
        return false;
    }

    public ListNode insert(ListNode head, int value){
        if (head == null){
            return new ListNode(value);
        }
        if (value <= head.value){
            ListNode newNode = new ListNode(value);
            newNode.next = head;
            return newNode;
        }
        ListNode left = head;
        ListNode right = head.next;
        while (right!=null){
            if (value >= left.value && value <=right.value){
                ListNode newNode = new ListNode(value);
                left.next = newNode;
                newNode.next = right;
                //return head;
            }
            else {
                left = left.next;
                right = right.next;
            }
        }
        if (value >= left.value){
            left.next = new ListNode(value);
            //return head;
        }
        return head;
    }

    public ListNode insert2(ListNode head , int value){
        if (head == null){
            return new ListNode(value);
        }
        if (value <= head.value){
            ListNode newNode = new ListNode(value);
            newNode.next = head;
            return newNode;
        }
        ListNode pointer = head;
        while (pointer.next!=null && pointer.next.value<value){
            pointer = pointer.next;
        }
        ListNode newNode = new ListNode(value);
        newNode.next = pointer.next;
        pointer.next = newNode;
        return head;
    }

    public ListNode merge(ListNode one, ListNode two){
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (one!=null && two!=null){
            if (one.value < two.value){
                cur.next = one;
                one = one.next;
            }
            else {
                cur.next = two;
                two = two.next;
            }
            cur = cur.next;
        }
        if (one == null) {
            cur.next = two;
        }
        else {
            cur.next = one;
        }
        return dummyNode.next;

    }



//    public ListNode partition(ListNode head, int x){
//        if (head == null) {
//            return null;
//        }
//        ListNode dummySmall = new ListNode(0);
//        ListNode dummyLarge = new ListNode(0);
//        ListNode tailSmall = dummySmall;
//        ListNode tailLarge = dummyLarge;
//        ListNode cur = head;
//        while (cur!=null){
//            if (cur.value<=x){
//                tailSmall.next = cur;
//
//            }
//        }
//    }
}
