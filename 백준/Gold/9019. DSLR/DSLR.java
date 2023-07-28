import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class DSLR {
    int value;
    String command;

    DSLR(int value, String command) {
        this.value = value;
        this.command = command;
    }
}

public class Main {
    static final int MAX = 10000;
    static boolean[] visited = new boolean[MAX];

    static int D(int n) {
        return (2 * n) % MAX;
    }

    static int S(int n) {
        return (n == 0) ? 9999 : n - 1;
    }

    static int L(int n) {
        return (n % 1000) * 10 + n / 1000;
    }

    static int R(int n) {
        return (n / 10) + ((n % 10) * 1000);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int A = sc.nextInt();
            int B = sc.nextInt();

            for (int i = 0; i < MAX; i++) {
                visited[i] = false;
            }

            Queue<DSLR> queue = new LinkedList<>();
            queue.add(new DSLR(A, ""));
            visited[A] = true;

            while (!queue.isEmpty()) {
                DSLR cur = queue.poll();
                if (cur.value == B) {
                    System.out.println(cur.command);
                    break;
                }

                int D_value = D(cur.value);
                int S_value = S(cur.value);
                int L_value = L(cur.value);
                int R_value = R(cur.value);

                if (!visited[D_value]) {
                    queue.add(new DSLR(D_value, cur.command + "D"));
                    visited[D_value] = true;
                }
                if (!visited[S_value]) {
                    queue.add(new DSLR(S_value, cur.command + "S"));
                    visited[S_value] = true;
                }
                if (!visited[L_value]) {
                    queue.add(new DSLR(L_value, cur.command + "L"));
                    visited[L_value] = true;
                }
                if (!visited[R_value]) {
                    queue.add(new DSLR(R_value, cur.command + "R"));
                    visited[R_value] = true;
                }
            }
        }
    }
}
