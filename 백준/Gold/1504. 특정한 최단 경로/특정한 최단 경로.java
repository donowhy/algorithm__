import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    private static int n, e, v1, v2;
    private static class Node implements Comparable<Node>{
        int e, c;
        public Node(int e, int c) {
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.c - o.c;
        }
    }

    private static ArrayList<Node>[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        n = Integer.parseInt(s[0]);
        e = Integer.parseInt(s[1]);

        nodeList = new ArrayList[n+1];

        for(int i=0; i<=n; i++) {
            nodeList[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++) {
            s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            int coin = Integer.parseInt(s[2]);

            nodeList[x].add(new Node(y, coin));
            nodeList[y].add(new Node(x, coin));
        }

        s = br.readLine().split(" ");
        v1 = Integer.parseInt(s[0]);
        v2 = Integer.parseInt(s[1]);


        int a = dijkstra(1, v1);
        int aa = dijkstra(1, v2);

        int b = dijkstra(v1, v2);

        int c = dijkstra(v1, n);
        int cc = dijkstra(v2, n);

        boolean flag = false;
        boolean flag2 = false;

        if(a == -1 || b == -1 || cc == -1) {
            flag2 = true;
        }

        if(aa == -1 || b == -1 || c == -1) {
            flag = true;
        }

        if(flag && flag2) {
            System.out.println(-1);
            return;
        }

        int result = Math.min(a + cc, c + aa) + b;

        System.out.println(result);
    }



    private static int dijkstra(int s, int e) {
        int[] dist = new int[n+1];
        for(int i=0; i<=n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.e == e) return node.c;
            for(Node next : nodeList[node.e]) {
                if(dist[next.e] <= node.c + next.c) continue;
                dist[next.e] = node.c + next.c;
                pq.offer(new Node(next.e, dist[next.e]));
            }
        }
        return -1;
    }
}
