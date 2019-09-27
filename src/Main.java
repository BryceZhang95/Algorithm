import java.util.LinkedList;

public class Main {

    private static int changeInt(int x){
        x = 10;
        return x;
    }
    public static void main(String[] args) {
        int[] array = {1,3,0,2,0,0,1,5,3,2,1,4,5,4};
        int[][] matrix = {{1,2,3},{2,4,5},{6,8,9}};
        LinkedList<Integer> stack = new LinkedList<Integer>(){
            {
                add(2);
                add(3);
                add(1);

            }
        };

        BinarySearch bs = new BinarySearch();
        StackAndQueue sq = new StackAndQueue();

//        sq.sort(stack);
//        Sort sort = new Sort();
//        MinHeap minHeap = new MinHeap(array);
//        minHeap.offer(5);
//        minHeap.update(18,0);
//        minHeap.print();
//        Practice practice = new Practice();
////        practice.replace("docomomomocomo","omo","bag");
//        DP dp = new DP();
//        System.out.println(dp.minCut("ababbbabbababa"));
//        int[] input = new int[] {2,3,0,1,4};
//        Integer a = new Integer(1);
//        Integer b = new Integer(1);
////        DFS dfs = new DFS();
////        dfs.combinationSum(input,7);
////        Practice practice = new Practice();
////        practice.calculate("3+2*2");
//        System.out.println(a==b);
//        System.out.println(a.equals(b));
//        DFS dfs = new DFS();
//        dfs.solveNQueens(4);
        int[][] input = new int[][]{{1,1},{2,2},{3,3}};
        Practice practice = new Practice();
        practice.maxPoints(input);



//        sq.sort(stack);
//        Sort sort = new Sort();
//        int[] result = sort.quickSort(array);
//        for (int i =0;i<result.length;i++){
//            System.out.println("result is "+result[i]);
//        }

//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = null;
//        LinkedList ll = new LinkedList();
//        ll.middleNode(node1);
//        int [] result = bs.kClosest(array,2,10);
//        for (int i =0;i<result.length;i++){
//            System.out.println("result is "+result[i]);
//        }
        //int result = bs.shiftPosition(array);
        //int[] result = bs.sortedMatrix1(matrix,7);
//        int[] result = bs.sortedMatrix2(matrix,5);
//        System.out.println("result is "+result[0]+result[1]);



    }
}
