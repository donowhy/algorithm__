import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class State implements Comparable<State> {
        int node;
        int k;
        long cost;

        public State(int node, int k, long cost) {
            this.node = node;
            this.k = k;
            this.cost = cost;
        }

        public int compareTo(State o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));
        }

        long[][] dist = new long[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        pq.add(new State(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            State curr = pq.poll();

            if (dist[curr.node][curr.k] < curr.cost) continue;

            for (Edge edge : graph[curr.node]) {
                long nextCost = curr.cost + edge.cost;
                if (nextCost < dist[edge.to][curr.k]) {
                    dist[edge.to][curr.k] = nextCost;
                    pq.add(new State(edge.to, curr.k, nextCost));
                }

                if (curr.k < k) {
                    if (curr.cost < dist[edge.to][curr.k + 1]) {
                        dist[edge.to][curr.k + 1] = curr.cost;
                        pq.add(new State(edge.to, curr.k + 1, curr.cost));
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            answer = Math.min(answer, dist[n][i]);
        }

        System.out.println(answer);
    }
}