package easy;

import java.math.BigInteger;
import java.util.Scanner;

public class boj_13458 {
    static int N; // n개의 시험장
    static int[] applicantsPerRoom;
    static int applicantsPerASupervisor;
    static int applicantsPerBSupervisor;
    static BigInteger minimumSupervisor = BigInteger.ZERO;

    public static void main(String[] args) {
        input();

        assignASupervisor();
        assignBSupervisor();

        System.out.println(minimumSupervisor);
    }

    public static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        applicantsPerRoom = new int[N];
        for(int i=0; i<N; i++) {
            applicantsPerRoom[i] = scan.nextInt();
        }

        applicantsPerASupervisor = scan.nextInt();
        applicantsPerBSupervisor = scan.nextInt();
    }

    public static void assignASupervisor() {
        for( int i=0; i<N; i++) {
            applicantsPerRoom[i] -= applicantsPerASupervisor;
            minimumSupervisor = minimumSupervisor.add(BigInteger.ONE);
        }
    }

    public static void assignBSupervisor() {
        for (int i=0; i<N; i++) {
            if (applicantsPerRoom[i] <= 0) {
                continue;
            } else {
                int temp = (int)Math.ceil(applicantsPerRoom[i]/(double)applicantsPerBSupervisor);
                minimumSupervisor = minimumSupervisor.add(BigInteger.valueOf(temp));
            }
        }
    }
}
