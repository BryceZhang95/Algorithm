import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DFS {
    public List<String> subSets(String set) {
        // Write your solution here.
        List<String> result = new ArrayList<String>();
        if (set == null) {
            return result;
        }

        char[] arraySet = set.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        helper1(arraySet,0,stringBuilder,result);
        return result;
    }

    public void helper1(char[] input, int index, StringBuilder stringBuilder, List<String> result) {

        if (index == input.length) {
            result.add(stringBuilder.toString());
            return;
        }

        stringBuilder.append(input[index]);
        helper1(input,index + 1,stringBuilder,result);
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        helper1(input,index+1,stringBuilder,result);
    }

    public List<String> validParentheses(int n) {
        // Write your solution here
        List<String> result = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder();
        helper2(result,0,2*n,stringBuilder,0,0);
        return result;
    }

    public void helper2(List<String> result, int index, int length, StringBuilder stringBuilder, int left, int right){
        if (index == length) {
            result.add(stringBuilder.toString());
            //System.out.println(result);
            return;
        }

        if (left < length/2) {
            stringBuilder.append('(');
            helper2(result,index+1,length,stringBuilder,left+1,right);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        if (left > right) {
            stringBuilder.append(')');
            helper2(result,index+1,length,stringBuilder,left,right+1);
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

    }

    public List<List<Integer>> combinations(int target, int[] coins) {
        // Write your solution here
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<Integer>();
        helper3(target,coins,result,cur,0);
        return result;
    }

    public void helper3(int target, int[] coins,List<List<Integer>> result, List<Integer> cur, int height ){

        if (height == coins.length - 1 ) {
            if (target % coins[height] == 0) {
                cur.add(target / coins[height]);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }

        int max = target / coins[height];
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            helper3(target - i * coins[height],coins,result,cur,height+1);
            cur.remove(cur.size()-1);
        }
    }

    public List<String> permutations(String set) {
        // Write your solution here
        List<String> result = new ArrayList<String>();
        char[] array = set.toCharArray();
        helper4(array,0,result);
        return result;
    }

    private void helper4(char[] array, int index, List<String> result){
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        HashSet<Character> set = new HashSet<>();

        for (int i = index; i < array.length; i++) {
            if (!set.contains(array[i])) {
                set.add(array[i]);
                swap(array,index,i);
                helper4(array,index+1,result);
                swap(array,index,i);
            }

        }
    }
    public int[][] spiralGenerate(int n) {
        // Write your solution here
        int[][] result = new int[n][n];
        helper(result,0,n,1,n);
        return result;
    }

    private void helper(int[][] result, int offset, int size, int cnt, int n) {
        if (size <= 1) {
            if (size == 1) {
                result[offset][offset] = cnt;
            }
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            result[offset][i+offset] = cnt++;
        }
        for (int j = 0; j < size - 1; j++) {
            result[j + offset][n - offset - 1] = cnt++;
        }
        for (int k = 0; k < size - 1; k++) {
            result[n - offset - 1][n - offset - 1 - k] = cnt++;
        }
        for (int l = 0; l < size - 1; l++) {
            result[n - offset - 1 - l][offset] = cnt++;
        }
        helper(result,offset+1,size-2,cnt,n);

    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        List<Integer> temp = new ArrayList<>();
        helper5(candidates,0,target,temp,result);
        return result;
    }

    private void helper5(int[] nums, int level, int left, List<Integer> temp, List<List<Integer>> result) {
        if (level == nums.length - 1) {
            if (left % nums[level] == 0) {
                for (int i = 0; i < left / nums[level]; i++) {
                    temp.add(nums[level]);
                }
                result.add(new ArrayList<>(temp));
                for (int i = 0; i < left / nums[level]; i++) {
                    temp.remove(temp.size() - 1);
                }
            }
            return;
        }

        int max = left / nums[level];
        for (int i = 0; i <= max; i++) {
            for (int j = 0; j < i; j++) {
                temp.add(nums[level]);
            }
            helper5(nums,level+1,left-i*nums[level],temp,result);
            for (int j = 0; j < i; j++) {
                temp.remove(temp.size()-1);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> tempResult = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String temp = "";
            for (int j = 0; j < n; j++) {
                temp += '.';
            }
            tempResult.add(temp);
        }
        List<Integer>  temp = new ArrayList<>();
        DFS(result,temp,tempResult,n);
        return result;
    }

    private void DFS(List<List<String>> result, List<Integer> temp, List<String> tempResult, int n) {
        if (temp.size() == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < temp.size(); i++) {
                String tmp = tempResult.get(i);
                StringBuilder sb = new StringBuilder(tmp);
                sb.setCharAt(temp.get(i),'Q');
                list.add(sb.toString());
            }
            result.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            temp.add(i);
            if (Check(temp)) {
                DFS(result,temp,tempResult,n);
            }
        }

    }
    private boolean Check(List<Integer> temp) {
        int current = temp.get(temp.size() - 1);
        for (int i = 0; i < temp.size() - 1; i++) {
            int tmp = temp.get(i);
            if (current == tmp || Math.abs(i - temp.size() + 1) == Math.abs(current - tmp)) {
                return false;
            }
        }
        return true;
    }

    private void swap(char[] input , int a, int b) {
        char temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
