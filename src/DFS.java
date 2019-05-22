import java.util.ArrayList;
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
}
