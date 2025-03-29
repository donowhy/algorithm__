import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] tr;
    static boolean[] visited;
    static boolean[] finished;
    static int count;

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());

            tr = new int[n];
            visited = new boolean[n];
            finished = new boolean[n];
            count = 0;

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                tr[i] = Integer.parseInt(input[i]) - 1;
            }

            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

            System.out.println(n - count);
        }
    }

    public static void dfs(int curr) {
        visited[curr] = true;
        int next = tr[curr];

        if (!visited[next]) {
            dfs(next);
        } else if (!finished[next]) {
            // 사이클 발견
            for (int i = next; i != curr; i = tr[i]) {
                count++;
            }
            count++;
        }

        finished[curr] = true;
    }
}
