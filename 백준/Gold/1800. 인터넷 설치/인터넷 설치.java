import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, p, k;
    private static List<Node>[] adj;
    private static int[] dist;

    private static class Node implements Comparable<Node> {
        int to, weight;
        public Node (int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        int maxCost = 0;
        for(int i=0; i<p; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Node(v,w));
            adj[v].add(new Node(u,w));
            maxCost = Math.max(maxCost, w);
        }

        int s = 0;
        int e = maxCost;
        int ans = -1;

        while(s <= e) {
            int mid = (s + e) / 2;

            if(canGo(mid)) {
                ans = mid;
                e = mid - 1;
            }
            else {
                s = mid + 1;
            }
        }
        System.out.println(ans);
    }

    private static boolean canGo(int limit) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int curIdx = current.to;
            int curUsedTickets = current.weight;
            
            if(curUsedTickets > dist[curIdx]) continue;
            
            for(Node next : adj[curIdx]) {
                int cost = (next.weight > limit) ? 1 : 0;
                
                if(dist[next.to] > dist[curIdx] + cost) {
                    dist[next.to] = dist[curIdx] + cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                } 
            }
        }
        return dist[n] <= k;
    }
}
