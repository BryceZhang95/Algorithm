

import java.util.*;
import java.util.LinkedList;


public class BFS {
    public List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Write your solution here
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> cur = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0 ; i < size ; i++ ) {
                // expand
                TreeNode temp = queue.poll();
                cur.add(temp.key);
                // generate
                if (temp.left!=null) {
                    queue.offer(temp.left);
                }
                if (temp.right!=null) {
                    queue.offer(temp.right);
                }

            }
            result.add(cur);
        }

        return result;


    }

    public boolean isBipartite(List<GraphNode> graph) {
        // write your solution here
        HashMap<GraphNode,Integer> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!BFS(node,map)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GraphNode,Integer> map) {
        if (map.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node,0);
        while (!queue.isEmpty()) {
            GraphNode temp = queue.poll();
            int curColor = map.get(temp);
            int neiColor = curColor == 0 ? 1 : 0;
            for (GraphNode neighbor : temp.neighbors) {
                if (!map.containsKey(neighbor)) {
                    map.put(neighbor,neiColor);
                    queue.offer(neighbor);
                } else if (map.get(neighbor)!= neiColor) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCompleted(TreeNode root) {
        // Write your solution here
//        if (root == null) {
//            return true;
//        }
//
//        Queue<TreeNode> queue = new LinkedList<TreeNode>();
//        boolean flag = false;
//
//        queue.offer(root);
//
//        while (!queue.isEmpty()) {
//           TreeNode temp = queue.poll();
//            // if flag is false :
//            // 1.left is null , right isn't null -> return false
//            // 2.left is null , right is null -> flag
//            // 3.left isn't null , right is null -> flag , append left
//            // 4.left isn't null , right isn't null -> continue , append left and right
//
//            // if flag is true;
//            // 1. return false
//            // 2.
//            // 3. return false
//            // 4. return false
//
//
//
//
//        }
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode temp;
        boolean flag = false;
        while (!queue.isEmpty()) {
            temp = queue.remove();
            if (temp == null){
                flag = true;
                continue;
            }
            if (flag) return false;
            queue.add(temp.left);
            queue.add(temp.right);
        }
        return true;


    }

    public int kthSmallest(int[][] matrix, int k) {
        // Write your solution here
        int rowLength = matrix.length;
        int colLength = matrix[0].length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return Integer.compare(o1.val,o2.val);
            }
        });
        boolean[][] vis = new boolean[rowLength][colLength];
        int topLeft = matrix[0][0];
        minHeap.offer(new Cell(0,0,topLeft));
        vis[0][0] = true;
        for (int i = 0; i < k-1; i++) {

            Cell cur = minHeap.poll();
            if (cur.row + 1 < rowLength && !vis[cur.row+1][cur.col]) {
                minHeap.offer(new Cell(cur.row+1,cur.col,matrix[cur.row+1][cur.col]));
                vis[cur.row+1][cur.col] = true;
            }

            if (cur.col + 1 < colLength && !vis[cur.row][cur.col+1]) {
                minHeap.offer(new Cell(cur.row,cur.col+1,matrix[cur.row][cur.col+1]));
                vis[cur.row][cur.col+1] = true;
            }

        }
        return minHeap.peek().val;

    }

    public int kthSmallest(int[] A, int[] B,int k) {
        // Write your solution here
        int rowLength = A.length;
        int colLength = B.length;
        PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return Integer.compare(o1.val,o2.val);
            }
        });
        boolean[][] vis = new boolean[rowLength][colLength];
        int topLeft = A[0] + B[0];
        minHeap.offer(new Cell(0,0,topLeft));
        vis[0][0] = true;
        for (int i = 0; i < k-1; i++) {

            Cell cur = minHeap.poll();
            if (cur.row + 1 < rowLength && !vis[cur.row+1][cur.col]) {
                minHeap.offer(new Cell(cur.row+1,cur.col,A[cur.row+1] + B[cur.col]));
                vis[cur.row+1][cur.col] = true;
            }

            if (cur.col + 1 < colLength && !vis[cur.row][cur.col+1]) {
                minHeap.offer(new Cell(cur.row,cur.col+1,A[cur.row] + B[cur.col+1]));
                vis[cur.row][cur.col+1] = true;
            }

        }
        return minHeap.peek().val;

    }

    static class Cell{
        int row;
        int col;
        int val;
        public Cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }


    class GraphNode {
        public int key;
        public List<GraphNode> neighbors;
        public GraphNode(int key) {
          this.key = key;
          this.neighbors = new ArrayList<GraphNode>();
        }
      }

}
