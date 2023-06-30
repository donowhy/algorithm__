import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] conn;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        int m = Integer.parseInt(line[2]);

        conn = new ArrayList[n + m + 1];
        for (int i = 1; i <= n + m; i++) {
            conn[i] = new ArrayList<>();
        }

        for (int i = n + 1; i <= n + m; i++) {
            line = br.readLine().split(" ");
            for (String l : line) {
                int station = Integer.parseInt(l);
                conn[i].add(station);
                conn[station].add(i);
            }
        }

        dist = new int[n + m + 1];
        Arrays.fill(dist, -1);

        bfs(1, n);

        if (dist[n] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(dist[n] / 2 + 1);
        }
    }

    static void bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : conn[now]) {
                if (dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    q.add(next);
                }
            }
        }
    }
}
