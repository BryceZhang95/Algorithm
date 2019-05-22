import java.util.*;
import java.util.LinkedList;

public class StringPractice {

    public List<Integer> common(List<Integer> A, List<Integer> B) {
        // Write your solution here
        List<Integer> result = new ArrayList<Integer>();
        if (A.size() == 0 || B.size() == 0) {
            return result;
        }
        int i=0,j=0;
        while (i<A.size()&&j<B.size()) {
            if (A.get(i) == B.get(j)) {
                result.add(A.get(i++));
                j++;
            } else if (A.get(i) < B.get(j)) {
                i++;
            } else {
                j++;
            }

        }
        return result;

    }

    public String remove(String input, String t) {
        // Write your solution here
        char[] inputArray = input.toCharArray();
        Set<Character> set = new HashSet<Character>();
        for (int i=0; i<t.length(); i++) {
            set.add(t.charAt(i));
        }
        int slow = 0;

        for (int fast=0; fast<inputArray.length; fast++) {
            if (!set.contains(inputArray[fast])) {
                inputArray[slow++] = inputArray[fast];
            }
        }

        return new String(inputArray,0,slow);
    }

    public String removeSpaces(String input) {
        // Write your solution here
        char[] inputArray = input.toCharArray();
        int slow = 0;
        for (int fast=0; fast<inputArray.length; fast++) {
            if ((fast == 0 && inputArray[fast] == ' ') || (inputArray[fast] == ' ' && inputArray[fast-1] == ' ')) {
                continue;
            }
            inputArray[slow++] = inputArray[fast];
        }
        if (slow>0 && inputArray[slow-1] == ' ') {
            return new String(inputArray,0,slow-1);
        }
        return new String(inputArray,0,slow);
    }

    public String deDup(String input) {
        // Write your solution here
        char[] inputArray = input.toCharArray();
        if (input == null || input.length() == 0) {
            return input;
        }
        int slow = 1;
        for (int fast = 1; fast < inputArray.length; fast++) {
            if (inputArray[fast] != inputArray[fast-1]) {
                inputArray[slow++] = inputArray[fast];
            }
        }

        return new String(inputArray,0,slow);
    }

    public String deDup2(String input) {
        // Write your solution here
        char[] inputArray = input.toCharArray();
        Deque<Character> stack = new ArrayDeque<Character>();
        int i = 0;
        while (i < inputArray.length) {
            if (stack.size() > 0 && inputArray[i] == stack.peekFirst()) {
                while (inputArray[i] == stack.peekFirst()) {
                    i++;
                }
                stack.pollFirst();
            } else {
                stack.offerFirst(inputArray[i++]);
            }
        }
//        for (int j = 0; j < stack.size(); j++) {
//            System.out.println(stack.pollFirst());
//        }
        int size = stack.size();
        char[] result = new char[size];
        for (int j = 0; j < size; j++) {
            result[size - 1 - j] = stack.pollFirst();
            System.out.println(result[size - 1 - j]);

        }
        return new String(result);

    }

    public int strstr(String large, String small) {
        // Write your solution here
        if (small.length() == 0) {
            return 0;
        }
        char[] largeArray = large.toCharArray();
        char[] smallArray = small.toCharArray();

        int fast = 0;
        int slow = 0;
        while (fast < largeArray.length ) {

        }

        if (slow == smallArray.length) {
            return fast - slow;
        } else {
            return -1;
        }
    }

    public String[] topKFrequent(String[] combo, int k) {
        // Write your solution here
        if (combo.length == 0) {
            return new String[0];
        }

        Map<String,Integer> hashMap = new HashMap<>();
        for (String s : combo) {
            Integer frequency = hashMap.get(s);
            if (frequency == null) {
                hashMap.put(s,1);
            } else {
                hashMap.put(s,frequency+1);
            }
        }

        PriorityQueue<Map.Entry<String,Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<String,Integer> entry : hashMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if(entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        String[] result = new String[minHeap.size()];
        for (int i = minHeap.size() - 1; i >=0 ; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }

    public String reverse(String input) {
        // Write your solution here
        char[] array = input.toCharArray();
        int left = 0;
        int right = array.length - 1;
        reverseHelper(array,left,right);
        return new String(array,left,right+1);

    }

    public String reverseWords(String input) {
        // Write your solution here
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        while (fast < array.length){
            while (fast < array.length &&array[fast]!=' ') {
                fast++;
            }
            reverseHelper(array,slow,fast-1);
            slow = fast + 1;
            fast++;

        }
        reverseHelper(array,0,array.length - 1);
        return new String(array);
    }

    public String rightShift(String input, int n) {
        // Write your solution here
        char[] array = input.toCharArray();
        if (array.length == 0) {
            return input;
        }
        int shift = n % array.length;
        reverseHelper(array,0,array.length-shift-1);
        reverseHelper(array,array.length-shift,array.length-1);
        reverseHelper(array,0,array.length-1);
        return new String(array);
    }

    public void reverseHelper(char[] input, int left, int right) {
        //String result = new String();
        if (left >= right) {
            return ;
        }
        swap(input,left,right);
        reverseHelper(input,left+1,right-1);
    }

    public void swap(char[] input , int a, int b) {
        char temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
    public void swap(int[] input , int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public String replace(String input, String source, String target) {
        // Write your solution here

        if (source.length() >= target.length()) {
            return replaceShorter(input,source,target);
        } else {
            return replaceLonger(input,source,target);
        }

    }

    private String replaceShorter(String input, String source, String target) {
        char[] array = input.toCharArray();
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

    private String replaceLonger(String input, String source, String target) {
        char[] array = input.toCharArray();
        ArrayList<Integer> matches = findOccurance(array,source);

        int fast = array.length - 1 ;
        int lastIndex = matches.size() - 1;
        char[] result = new char[array.length + (target.length() - source.length())*matches.size()];
        int slow =result.length - 1;
        while (fast >= 0) {
            if (lastIndex>=0 && fast == matches.get(lastIndex)) {
                strCopy(result,target,slow - target.length() + 1);
                slow-=target.length();
                fast-=source.length();
                lastIndex--;
            } else {
                result[slow--] = array[fast--];
            }
        }
        return new String(result);
    }

    private boolean isEqual(char[] array, String source, int startIndex) {
        for (int i = 0; i < source.length(); i++) {
            if (array[startIndex + i]!=source.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private void strCopy(char[] array, String target, int startIndex) {
        for (int i = 0; i < target.length(); i++) {
            array[startIndex + i] = target.charAt(i);
        }
    }

    private ArrayList<Integer> findOccurance(char[] array, String subString) {
        ArrayList<Integer> matches = new ArrayList<Integer>();
        int i = 0;
        while (i <= array.length - subString.length()) {
            if (isEqual(array,subString,i)) {
                matches.add(i + subString.length() - 1);
                i+=subString.length();

            } else {
                i++;
            }
        }
        return matches;
    }

    public int[] reorder(int[] array) {
        // Write your solution here
        if (array.length % 2 == 0) {
            reorder(array, 0 , array.length - 1);
        } else {
            reorder(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorder(int[] array, int left, int right) {
        int size = right - left + 1;
        if (size<=2) {
            return;
        }
        int mid = left + size/2;
        int leftMid = left + size/4;
        int rightMid = left + size*3/4;
        reverse(array,leftMid,mid-1);
        reverse(array,mid,rightMid-1);
        reverse(array,leftMid,rightMid-1);

        reorder(array,left,left+(leftMid-left)*2-1);
        reorder(array,left+(leftMid-left)*2,right);

    }

    public void reverse(int[] input, int left, int right) {
        //String result = new String();
        if (left >= right) {
            return ;
        }
        swap(input,left,right);
        reverse(input,left+1,right-1);
    }

    public int longest(String input) {
        // Write your solution here
        char[] array = input.toCharArray();
        int slow = 0;
        int fast = 0;
        int max = 0;
        Set<Character> hashSet = new HashSet<>();
        while (fast < array.length) {
            if (hashSet.contains(array[fast]) ) {
                hashSet.remove(array[slow++]);
            } else {
                hashSet.add(array[fast++]);
                max = Math.max(max,fast-slow);
            }
        }
        return max;
    }

//    public List<Integer> allAnagrams(String sh, String lo) {
//        // Write your solution here
//        List<Integer> result = new ArrayList<Integer>();
//        if (lo.length() == 0 || sh.length() > lo.length()) {
//            return result;
//        }
//
//        Map<Character,Integer> hashMap = new HashMap<>();
//        for (char i:sh.toCharArray()) {
//            Integer count = hashMap.get(i);
//            if (count == null) {
//                hashMap.put(i,1);
//            } else {
//                hashMap.put(i,count+1);
//            }
//        }
//
//        int slow = 0;
//        int fast = sh.length() ;
//        while (fast < lo.length()) {
//            if (hashMap.containsKey(lo.charAt(fast))) {
//
//            }
//        }
//
//    }
}

