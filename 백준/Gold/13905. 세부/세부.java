import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, t1, t2;
    private static class Node {
        int arrival, cost;

        public Node(int arrival, int cost) {
            this.arrival = arrival;
            this.cost = cost;
        }
    }
    private static ArrayList<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        t1 = Integer.parseInt(st.nextToken());
        t2 = Integer.parseInt(st.nextToken());

        nodes = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[start].add(new Node(end, cost));
            nodes[end].add(new Node(start, cost));
        }

        System.out.println(bs());
    }

    private static int bs() {
        int max = 1_000_000;
        int min = 1;
        int result = 0;

        while(min <= max) {
            int mid = min + (max - min) / 2;

            if(bfs(mid)) {
                result = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return result;
    }

    private static boolean bfs(int value) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(t1);
        boolean[] visited = new boolean[n + 1];
        visited[t1] = true;

        while(!q.isEmpty()) {
            int now = q.poll();

            if(now == t2) {
                return true;
            }

            for(Node next : nodes[now]) {
                if(next.cost < value) continue;
                if(visited[next.arrival]) continue;

                visited[next.arrival] = true;
                q.offer(next.arrival);
            }
        }
        return false;
    }
}