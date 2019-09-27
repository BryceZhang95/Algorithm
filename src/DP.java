public class DP {
    public long fibonacci(int K) {
        if (K == 0 || K <0 ) {
            return 0;
        }
        if (K == 1) {
            return 1;
        }
        long[] a = new long[2];
        a[0] = 0;
        a[1] = 1;
        for (int i = 1; i < K; i++) {
            long temp = a[0] + a[1];
            a[0] = a[1];
            a[1] = temp;
        }
        return a[1];

    }

    public int longest(int[] array) {
        // Write your solution here
        int[] M = new int[array.length];
        M[0] = 1;
        int max = 1;
        for (int i = 1; i < array.length; i++) {
            if (M[i] >= array[i-1]) {
                M[i] = M[i-1] + 1;
            } else {
                M[i] = 1;
            }
            if (M[i] > max) {
                max = M[i];
            }
        }
        return max;
    }

    public int maxProduct(int length) {
        // Write your solution here
        int[] M = new int[length+1];
        M[0] = 0;
        M[1] = 0;
        for (int i = 2; i <= length; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++ ) {
                curMax = Integer.max(curMax,Integer.max(j,M[j]) * (i-j));
            }
            M[i] = curMax;
        }

        return M[length];
    }

    public boolean canJump(int[] array) {
        // Write your solution here
        boolean[] M = new boolean[array.length];
        M[0] = true;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (M[j]==true && array[j] + j >=i) {
                    M[i] = true;
                    break;
                }
            }
        }

        return M[array.length-1];

    }

    public int largest(int[][] matrix) {
        // Write your solution here
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int col = matrix[0].length;
        if (col == 0) {
            return 0;
        }
        int[][] right = new int[row][col];
        int[][] left = new int[row][col];
        int[][] up = new int[row][col];
        int[][] down = new int[row][col];
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            right[i] = longestOneRight(matrix[i]);
        }
        for (int i = 0; i < row; i++) {
            left[i] = longestOneLeft(matrix[i]);
        }
        longestOneDown(matrix,down,row,col);
        longestOneUp(matrix,up,row,col);

        int globalMax = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = Integer.min(Integer.min(right[i][j],left[i][j]),Integer.min(up[i][j],down[i][j]));
                if (result[i][j] > globalMax) {
                    globalMax = result[i][j];
                }
            }
        }
        print(right);
        print(left);
        print(up);
        print(down);
        return globalMax;


    }
    private int[] longestOneLeft(int[] input) {
        int[] M = new int[input.length];
        M[input.length - 1] = input[input.length - 1];
        for (int i = input.length - 2; i >= 0; i--) {
            if (input[i] == 0) {
                M[i] = 0;
            } else {
                M[i] = M[i+1] + 1;
            }
        }
        return M;
    }
    private int[] longestOneRight(int[] input) {
        int[] M = new int[input.length];
        M[0] = input[0];
        for (int i = 1; i < input.length; i++) {
            if (input[i] == 0) {
                M[i] = 0;
            } else {
                M[i] = M[i-1] + 1;
            }
        }
        return M;
    }

    private void longestOneDown(int[][] input, int[][] output, int row, int col) {
        for (int i = 0; i < col; i ++) {
            output[0][i] = input[0][i];
            for (int j = 1; j < row; j++) {
                if (input[j][i] == 0) {
                    output[j][i] = 0;
                } else {
                    output[j][i] = output[j-1][i] + 1;
                }
            }
        }
    }

    private void longestOneUp(int[][] input, int[][] output, int row, int col) {
        for (int i = 0; i < col; i ++) {
            output[row - 1][i] = input[row - 1][i];
            for (int j = row - 2; j >= 0; j--) {
                if (input[j][i] == 0) {
                    output[j][i] = 0;
                } else {
                    output[j][i] = output[j+1][i] + 1;
                }
            }
        }
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i ++) {
            for (int j=0; j < matrix[0].length; j ++) {
                System.out.println(matrix[i][j]);
            }
        }
    }

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = 0;
        int n = s.length();
        boolean[][] m = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >=0; j--) {
                if (s.charAt(j) == s.charAt(i) && (i - j < 2 ||m[j+1][i-1]) ) {
                    m[j][i] = true;
                    count++;
                }
            }
        }
        return count;

    }

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[] m = new int[n];
        for (int i = 0 ; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (s.charAt(j) == s.charAt(i) &&((i - j < 2) || dp[j + 1][i - 1]) ) {
                    dp[j][i] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (dp[0][i]) {
                m[i] = 0;
            } else {
                m[i] = Integer.MAX_VALUE;
                for (int j = 0; j < i; j++) {
                    if (dp[j+1][i] == true && (1 + m[j] < m[i])) {
                        m[i] = 1 + m[j];
                    }
                }
            }
        }
        return m[n-1];
     }
}
