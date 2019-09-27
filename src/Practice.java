import java.util.*;
import java.util.LinkedList;

class RandomListNode {
    int value;
    RandomListNode next;
    RandomListNode random;
    RandomListNode (int value) {
        this.value = value;
    }
}

class Element{
    int value;
    int index;
    public Element(int value,int index) {
        this.value = value;
        this.index = index;
    }
}


class GraphNode {
    int key;
    List<GraphNode> neighbors;
    GraphNode (int key) {
        this.key = key;
    }
}

class Entry {
    int row;
    int col;
    int val;
    Entry (int row, int col, int val) {
        this.row = row;
        this.col = col;
        this.val = val;
    }
}
public class Practice {
    public int[] mergeSort(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int[] helper = new int[array.length];
        merge(array,helper,0,array.length-1);
        return array;

    }
    public void merge(int[] array, int[] helper,int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        merge(array,helper,left,mid);
        merge(array,helper,mid+1,right);
        combine(array,helper,left,right,mid);

    }
    public void combine(int[] array,int[] helper, int left, int right, int mid) {
        for (int index = left; index <=right; index++) {
            helper[index] = array[index];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (helper[i] < helper[j]) {
                array[k++] = helper[i];
            } else {
                array[k++] = helper[j];
            }
        }
        while (i <= mid) {
            array[k++] = helper[i];
        }
    }
    public String reverse(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            while (fast < array.length && array[fast] != ' ') {
                fast++;
            }
            reverseHelper(array,slow,fast-1);
            slow = fast + 1;
            fast++;
        }
        reverseHelper(array,0,array.length-1);
        return new String(array.toString());
    }

    private void reverseHelper(char[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(array,left,right);
        reverseHelper(array,left+1, right-1);
    }

    private void reverseHelper(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(array,left,right);
        reverseHelper(array,left+1, right-1);
    }

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>();
        int index = 0;
        for (int num : nums) {
            map.put(num,index++);
        }

        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int number = target - nums[i];
            if (map.containsKey(number) && map.get(number) != i) {
                result[0] = i;
                result[1] = map.get(number);
            }
        }


        return result;
    }

    public String replace(String input, String source, String target) {
        char[] array = input.toCharArray();
        if (source.length() >= target.length()) {
            return replaceShorter(array,source,target);
        } else {
            return replaceLonger(array,source,target);
        }
    }

    private String replaceShorter(char[] array, String source, String target) {
        int slow = 0;
        int fast = 0;
        while (fast < array.length) {
            if (fast <= array.length - source.length() && isEqual(array,source,fast)) {
                strCopy(array,target,slow);
                slow += target.length();
                fast += source.length();
            } else {
                array[slow++] = array[fast++];
            }
        }
        return new String(array,0,slow);
    }

    private String replaceLonger(char[] array, String source, String target) {
        List<Integer> occurance = new ArrayList<>();
        findOccurance(array,source,target,occurance);
        int space = (target.length() - source.length()) * occurance.size();
        char[] result = new char[array.length + space];
        int fast = array.length - 1;
        int slow = result.length - 1;
        int index = occurance.size() - 1;
        while (fast >=0) {
            if (index >= 0 && fast == (occurance.get(index) + source.length() - 1)) {
                strCopy(result,target,slow-target.length()+1);
                slow-=target.length();
                fast-=source.length();
                index--;
            } else {
                result[slow--] = array[fast--];
            }
        }
        return new String(result);
    }

    private boolean isEqual(char[] array, String source, int startIndex) {
        int i = 0;
        int j = startIndex;
        while (i < source.length()) {
            if (array[j]!=source.charAt(i)) {
                return false;
            } else {
                j++;
                i++;
            }
        }
        return true;
    }

    private void strCopy(char[] array, String target, int startIndex) {
        int i = 0;
        int j = startIndex;
        while (i < target.length()) {
            array[j++] = target.charAt(i++);
        }
    }

    private void findOccurance(char[] array, String source, String target, List<Integer> result ) {
        int i = 0;
        while (i <= array.length - source.length()) {
            if (array[i] == source.charAt(0) && isEqual(array,source,i)) {
                result.add(i);
                i += source.length();
            } else {
                i++;
            }
        }

    }

    public String compress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int slow = 0;
        int fast = 0;
        while (fast < input.length()) {
            while (fast < input.length() && input.charAt(fast)==input.charAt(slow)) {
                fast++;
            }
            int length = fast - slow;
            stringBuilder.append(input.charAt(slow)).append(length);
            slow += length;

        }
        return stringBuilder.toString();

    }

    public String decompress(String input) {
        // Write your solution here
        if (input == null || input.length() == 0) {
            return input;
        }
        char [] array = input.toCharArray();
        return deLonger(array, deShorter(array));


    }

    private int deShorter(char[] array) {
        int end = 0;
        for (int i = 0; i < array.length; i+=2) {
            int digit = getDigit(array[i+1]);
            if (digit <=2 && digit >= 0) {
                for (int j = 0; j < digit; j++) {
                    array[end++] = array[i];
                }
            } else {
                array[end++] = array[i];
                array[end++] = array[i+1];
            }
        }
        return end;
    }

    private String deLonger(char[] array, int length) {
        int newLength = length;
        for (int i = 0; i < length; i++) {
            int digit = getDigit(array[i]);
            if (digit > 2 && digit <= 9) {
                newLength+=(digit - 2);
            }
        }
        char[] result = new char[newLength];
        int end = newLength - 1;
        for (int i = length - 1; i >=0; i--) {

            if (getDigit(array[i]) > 2 && getDigit(array[i]) <= 9) {
                i--;
                for (int j = 0; j < getDigit(array[i]); j++) {
                    result[end--] = array[i];
                }
            } else {
                result[end--] = array[i];
            }
        }
        return new String(result);
    }

    private int getDigit(char a) {
        return a - '0';
    }

    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;

        Map<RandomListNode,RandomListNode> map = new HashMap<>();
        while (head != null) {
            if (!map.containsKey(head)) {
                map.put(head,new RandomListNode(head.value));
            }
            cur.next = map.get(head);
            if (head.random!=null) {
                if (!map.containsKey(head.random.value)) {
                    map.put(head.random,new RandomListNode(head.random.value));
                }
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;

    }

    public List<GraphNode> copy (List<GraphNode> graph) {

        if (graph == null) {
            return null;
        }
        Map<GraphNode,GraphNode> map = new HashMap<>();
        Queue<GraphNode> queue = new LinkedList<>();

        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node,new GraphNode(node.key));
                DFS(node,map);
            }
        }
        return new ArrayList<GraphNode>(map.values());

    }
    private void DFS(GraphNode root,Map<GraphNode,GraphNode> map) {
        GraphNode copy = map.get(root);
        for (GraphNode nei : copy.neighbors){
            if (!map.containsKey(nei)) {
                map.put(nei,new GraphNode(nei.key));
                DFS(nei,map);
            }
            copy.neighbors.add(map.get(nei));
        }
    }

    private void BFS(GraphNode root,Map<GraphNode,GraphNode> map, Queue<GraphNode> queue) {
        //GraphNode copy = map.get(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            GraphNode copy = queue.poll();
            for (GraphNode nei : copy.neighbors) {
                if (!map.containsKey(nei)) {
                    map.put(nei,new GraphNode(nei.key));
                }
                //copy.neighbors.add(map.get(nei));
                map.get(copy).neighbors.add(nei);
                queue.offer(nei);
            }
        }
    }

    public int[] merge(int[][] arrayOfArrays) {
        // Write your solution here
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(11, new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.val == o2.val) {
                    return 0;
                }
                return o1.val < o2.val ? -1 : 1;
            }
        });
        int length = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            int[] array = arrayOfArrays[i];
            length += array.length;
            minHeap.offer(new Entry(i,0,array[0]));
        }
        int[] result = new int[length];
        int cur = 0;
        for (int i = 0; i < length; i++) {
            Entry tmp = minHeap.poll();
            result[cur++] = tmp.val;
            if (tmp.col + 1 < arrayOfArrays[tmp.row].length) {
                tmp.col ++;
                tmp.val = arrayOfArrays[tmp.row][tmp.col];
                minHeap.offer(tmp);
            }
        }
        return result;
    }

    public ListNode merge(List<ListNode> listOfLists) {
        // Write your solution here/.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(11, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (ListNode node : listOfLists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode tmp = minHeap.poll();
            cur.next = tmp;
            if (tmp.next != null) {
                tmp = tmp.next;
                minHeap.offer(tmp);
            }
            cur = cur.next;

        }
        return dummy.next;
    }

    public boolean match(String input, String pattern) {
        // Write your solution here
        if (input == null && pattern == null) {
            return true;
        }
        if (input == null || pattern == null) {
            return false;
        }
        return helper(input,pattern,0,0);

    }
    private boolean helper(String input, String pattern, int inputStart, int patternStart) {
        if (inputStart > input.length() - 1 && patternStart > pattern.length() - 1) {
            return true;
        }
        if (inputStart > input.length() - 1 || patternStart > pattern.length() - 1) {
            return false;
        }
        if (Character.isLetter(pattern.charAt(patternStart))) {
            if (pattern.charAt(patternStart) != input.charAt(inputStart)) {
                return false;
            } else {
                return helper(input,pattern,inputStart+1,patternStart+1);
            }
        } else {
            int i = patternStart;
            int num = 0;
            while (i < pattern.length() && Character.isDigit(pattern.charAt(i))) {
                num = num * 10 + pattern.charAt(i) - '0';
                i++;
            }
            if (inputStart + num > input.length()) {
                return false;
            } else {
                return helper(input,pattern,inputStart+num,i);
            }
        }
    }
    public List<Integer> maxWindows(int[] array, int k) {
        // Write your solution here
        if (array == null || array.length == 0) {
            return null;
        }
        int n = array.length;
        List<Element> list = helper(array);
        List<Integer> result = new ArrayList<>();
        Queue<Element> maxHeap = new PriorityQueue<>(new Comparator<Element>(){
            @Override
            public int compare(Element o1, Element o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value > o2.value ? -1 : 1;
            }
        });
        int i = 0;
        while (i < k) {
            maxHeap.offer(list.get(i++));
        }
        result.add(maxHeap.peek().value);
        while (i < array.length) {
            maxHeap.offer(list.get(i++));
            while (maxHeap.peek().index < i - k  || maxHeap.peek().index > i - 1) {
                maxHeap.poll();
            }
            result.add(maxHeap.peek().value);
        }
        return result;
    }
    private List<Element> helper(int[] array) {
        List<Element> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(new Element(array[i],i));
        }
        return list;
    }

    public void wiggleSort(int[] nums) {
        //List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        for (int num : nums) {
            input.add(num);
        }
        int length = nums.length;
        helper(temp, 0, input,length);
        for (int i = 0; i < length; i++) {
            nums[i] = temp.get(i);
        }


    }

    private void helper(List<Integer> temp, int level, List<Integer> input,int length) {
        if (temp.size() == length) {
            return;
        }
        for (int i = 0; i < input.size(); i++) {
            if (level == 0) {
                temp.add(input.get(i));
                input.remove(i);
                helper(temp,level+1,input,length);
            } else if (level % 2 == 0) {
                if (input.get(i) < temp.get(temp.size() - 1)) {
                    temp.add(input.get(i));
                    input.remove(i);
                    helper(temp,level+1,input,length);
                }
            } else {
                if (input.get(i) > temp.get(temp.size() - 1)) {
                    temp.add(input.get(i));
                    input.remove(i);
                    helper(temp,level+1,input,length);
                }
            }
        }
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        int n1 = num1.length();
        int n2 = num2.length();
        int[][] result = new int[n1*n2][n1+n2];
        for (int i = 0; i < n1 * n2; i++) {
            for (int j = 0; j < n1 + n2; j++) {
                result[i][j] = 0;
            }
        }
        for (int j = 0; j < n2; j++) {
            for (int i = 0; i < n1; i++) {
                int temp = (num1.charAt(n1 - 1 - i) - '0') * (num2.charAt(n2 - 1 - j) - '0');
                int end = n1 + n2 - 1 - (i + j);
                while (temp != 0) {
                    result[i + n1 * j][end--] = temp % 10;
                    temp /= 10;
                }
            }
        }
        char[] num = new char[n1 + n2];
        int flag = 0;
        for (int i = n1 + n2 - 1; i >= 0; i--) {
            int temp = 0;
            for (int j = 0; j < n1 * n2; j++) {
                temp += result[j][i];
            }
            temp += flag;
            if (temp >= 10) {

                flag = temp / 10;
                temp %= 10;
            } else {
                flag = 0;
            }
            num[i] = (char) (temp + '0');

        }
        return num.toString();



    }

    public int calculate(String s) {
        int ans = 0;
        if (s == null || s.length() == 0) {
            return ans;
        }
        Deque<Integer> deque = new LinkedList<>();
        char operator = '+'; // 记录上一个操作符
        int tmp = 0;
        for (int i = 0; i < s.length() || tmp > 0; i++) {
            char c = i < s.length() ? s.charAt(i) : '+';
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                tmp = tmp * 10 + c - '0';
            } else {
                switch (operator) {
                    case '+':
                        deque.add(tmp);
                        break;
                    case '-':
                        deque.add(-tmp);
                        break;
                    case '*':
                        deque.add(deque.pollLast() * tmp);
                        break;
                    case '/':
                        deque.add(deque.pollLast() / tmp);
                        break;
                }
                tmp = 0;
                operator = c;
            }
        }
        for (int v : deque) {
            ans += v;
        }
        return ans;
    }

    public List<String> addSpace(String input) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        if (input == null || input.length() == 0) {
            sb.toString();
        }
        helper(input,sb,result,0);
        return result;

    }

    private void helper(String input, StringBuilder sb, List<String> result,int level) {
        if (level == input.length()) {
            result.add(sb.toString());
            return ;
        }
        sb.append(input.charAt(level));
        helper(input,sb,result,level+1);
        sb.append(' ');
        helper(input,sb,result,level+1);
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

    }

    public boolean isCousin(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flagA = false;
            boolean flagB = false;
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                TreeNode left = null;
                TreeNode right = null;
                if (temp.left != null) {
                    left = temp.left;
                    queue.offer(left);
                }
                if (temp.right != null) {
                    right = temp.right;
                    queue.offer(right);
                }
                if (left == a && right == b || left == b && right == a) {
                    return false;
                }
                flagA = (left == a || right == a || flagA);
                flagB = (left == b || right == b || flagB);
            }
            if (flagA && flagB) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        for (int i = 1; i <= n; i++) {
            result.add(construct(new TreeNode(i),1,i-1,i+1,n));
        }
        return result;
    }
    public TreeNode construct(TreeNode root,int sl,int sr,int ll, int lr) {
        if (sl > sr && ll > lr) {
            return root;
        }
        if (sl > sr) {
            root.left = null;
        } else {
            for (int i = sl; i <= sr; i++) {
                root.left = construct(new TreeNode(i),sl,i-1,i+1,sr);
            }
        }
        if (ll > lr) {
            root.right = null;
        } else {
            for (int i = ll; i <= lr; i++) {
                root.right = construct(new TreeNode(i),ll,i-1,i+1,lr);
            }
        }
        return root;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }
        for (int i = 0; i < nums.length - 3 ; i++) {
            if ( i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if ( j > i+1 && nums[j] == nums[j-1]) continue;
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int cur = nums[i] + nums[j] + nums[left] + nums[right];
                    if (cur == target) {
                        result.add(Arrays.asList(nums[i],nums[j],nums[left++],nums[right--]));
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    } else if (cur < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] m = new int[nums.length];
        m[nums.length - 1] = 0;
        for (int i = nums.length - 2; i >=0; i--) {
            m[i] = Integer.MAX_VALUE;
            for (int j = i + 1; (j <= i + nums[i]) && (j < nums.length) && (m[j] != Integer.MAX_VALUE); j++) {
                m[i] = Math.min(m[i],1 + m[j]);
            }
        }
        return m[1];
    }
    private int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] temp = queue.remove();
                for (int[] d : directions) {
                    int r = temp[0] + d[0];
                    int c = temp[1] + d[1];
                    if (r >= 0 && r < m && c >= 0 && c < n) {
                        if (grid[r][c] == 1) {
                            grid[r][c] = 2;
                            queue.add(new int[]{r, c});
                        }
                    }
                }
            }
            count++;
        }

        for (int[] row : grid) {
            for (int num : row) {
                if (num == 1)
                    return -1;
            }
        }
        // 因为最后一次遍历队列, 网格中所有橘子已经全部腐烂, count多加了一个1.
        return count == 0 ? 0 : count - 1;
    }

    public static class NetNode {
        int u;
        // 邻居节点及距离
        HashMap<Integer, Integer> neighbors = new HashMap<>();
        int distance = Integer.MAX_VALUE;

        public NetNode(int u) {
            this.u = u;
        }
    }


    public int networkDelayTime(int[][] times, int N, int K) {
        HashMap<Integer, NetNode> map = new HashMap<>();
        PriorityQueue<NetNode> queue = new PriorityQueue<>(new Comparator<NetNode>() {
            @Override
            public int compare(NetNode o1, NetNode o2) {
                return o1.distance - o2.distance;
            }
        });

        // 初始化节点
        for (int i = 1; i <= N; i++) {
            NetNode node = new NetNode(i);
            if (i == K) {
                // 初始化当前节点的距离为0
                node.distance = 0;
            }
            queue.offer(node);
            map.put(i, node);
        }

        // 更新邻居节点信息
        for (int[] time : times) {
            NetNode node = map.get(time[0]);
            node.neighbors.put(time[1], time[2]);
        }

        while (!queue.isEmpty()) {
            NetNode node = queue.poll();
            if (node.distance == Integer.MAX_VALUE) {
                // 存在节点无法到达
                return -1;
            }
            for (Map.Entry<Integer, Integer> entry : node.neighbors.entrySet()) {
                int u = entry.getKey();
                NetNode currentNode = map.get(u);
                // 存在更短的路径，更新路径信息
                if (node.distance + entry.getValue() < currentNode.distance) {
                    currentNode.distance = node.distance + entry.getValue();
                    queue.remove(currentNode);
                    queue.add(currentNode);
                }
            }
        }
        int max = 0;
        for (Map.Entry<Integer, NetNode> entry : map.entrySet()) {
            if (entry.getValue().distance > max) {
                max = entry.getValue().distance;
            }
        }
        return max;
    }

    public void solveSudoku(char[][] board) {
        boolean[][] rowUsed = new boolean[9][10];
        boolean[][] colUsed = new boolean[9][10];
        boolean[][] cellUsed = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int cell = 3 * (i / 3) + j / 3;
                    int digit = board[i][j] - '0';
                    rowUsed[i][digit] = true;
                    colUsed[j][digit] = true;
                    cellUsed[cell][digit] = true;
                } else {
                    DFS(board, i, j, rowUsed, colUsed, cellUsed);
                }
            }
        }
    }

    private void DFS(char[][] board, int x, int y, boolean[][] rowUsed, boolean[][] colUsed, boolean[][] cellUsed) {
        if (x > 8) {
            x %= 9;
            y++;
        }

        if (y > 8) {
            return;
        }
        int cell = 3 * (x / 3) + y / 3;
        int digit = board[x][y] - '0';
        if (board[x][y] != '.') {
            rowUsed[x][digit] = true;
            colUsed[y][digit] = true;
            cellUsed[cell][digit] = true;
            DFS(board, x + 1, y, rowUsed, colUsed, cellUsed);
        }

        for (int i = 1; i <= 9; i++) {
            if (!rowUsed[x][i] && !colUsed[y][i] && !cellUsed[cell][i]) {
                rowUsed[x][i] = true;
                colUsed[y][i] = true;
                cellUsed[cell][i] = true;
                board[x][y] = (char)(i + '0');
                DFS(board, x + 1, y, rowUsed, colUsed, cellUsed);
                board[x][y] = '.';
                rowUsed[x][i] = false;
                colUsed[y][i] = false;
                cellUsed[cell][i] = false;

            }
        }
    }

    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Map<int[], Set<int[]>> map1 = new HashMap<>();
        Map<Integer, Set<int[]>> map2 = new HashMap<>();
        for (int i = 1; i < points.length; i++) {
            for (int j = 0; j < i; j++) {
                int[] temp1 = points[j];
                int[] temp2 = points[i];
                if (temp1[0] == temp2[0]) {
                    if (!map2.containsKey(temp1[0])) {
                        Set<int[]> set = new HashSet<>();
                        set.add(temp1);
                        set.add(temp2);
                        map2.put(temp1[0], set);
                    } else {
                        Set set = map2.get(temp1[0]);
                        set.add(temp1);
                        set.add(temp2);
                        map2.put(temp1[0], set);
                    }
                } else {
                    int k = (temp2[1] - temp1[1]) / (temp2[0] - temp1[0]);
                    int b = temp2[1] - k * temp2[0];
                    int[] temp = new int[]{k,b};
                    if (!map1.containsKey(temp)) {
                        Set<int[]> set = new HashSet<>();
                        set.add(temp1);
                        set.add(temp2);
                        map1.put(temp, set);
                    } else {
                        Set set = map1.get(temp);
                        set.add(temp1);
                        set.add(temp2);
                        map1.put(temp, set);
                    }
                }
            }
        }
        int globalMax = 0;
        for (Set<int[]> v : map1.values()) {
            globalMax = Math.max(globalMax, v.size());
        }
        for (Set<int[]> v : map2.values()) {
            globalMax = Math.max(globalMax, v.size());
        }
        return globalMax;
    }



    private void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
