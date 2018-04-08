package dfs;

import java.util.Scanner;

public class boj_14888 {

    public static int N; // 숫자 개수
    public static int[] nums;
    public static int[] ops = new int[4];

    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        input();

        solveByDfs(nums[0], 0);

        System.out.println(max);
        System.out.println(min);
    }

    public static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        nums = new int[N];
        for (int i=0; i<N; i++) {
            nums[i] = scan.nextInt();
        }

        for (int i=0; i<ops.length; i++) {
            ops[i]= scan.nextInt();
        }
    }

    public static void solveByDfs(int result, int depth) {
        if (depth == N-1) {
            max = Integer.max(max, result);
            min = Integer.min(min, result);
            return;
        }

        // +, -, *, / 경우를 현재 스텝에서 다 봐야함
        for (int i=0; i<4; i++) {
            if (ops[i] != 0) {
                switch(i) {
                    case 0:
                        ops[0]--;
                        solveByDfs(result + nums[1+depth], depth+1);
                        ops[0]++;
                        break;
                    case 1:
                        ops[1]--;
                        solveByDfs(result - nums[1+depth], depth+1);
                        ops[1]++;
                        break;
                    case 2:
                        ops[2]--;
                        solveByDfs(result * nums[1+depth], depth+1);
                        ops[2]++;
                        break;
                    case 3:
                        ops[3]--;
                        solveByDfs(result / nums[1+depth], depth+1);
                        ops[3]++;
                        break;
                }
            }
        }
    }

}
