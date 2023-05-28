import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int v;
    static int e;
    static int[] parent;
    static PriorityQueue<Edge> q;
    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int cost;

        public Edge(int a, int b, int c){
            this.v1 = a;
            this.v2 = b;
            this.cost = c;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    public static void union(int n1, int n2){
        int p1 = find(n1);
        int p2 = find(n2);
        if(p1 < p2) parent[p2] = p1;
        else parent[p1] = p2;
    }

    public static int find(int n){
        if (parent[n] == n) return n;

        return parent[n] = find(parent[n]);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        q = new PriorityQueue<>();
        parent = new int[v+1];

        for(int i=0; i<= v; i++){
            parent[i] = i;
        }


        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            q.offer(new Edge(a, b, c));
        }

        int cost = 0;
        int maxEdgeCost = 0;

        while (!q.isEmpty()){
            Edge edge = q.poll();

            if (find(edge.v1) != find(edge.v2)) {
                union(edge.v1, edge.v2);
                cost += edge.cost;
                maxEdgeCost = Math.max(maxEdgeCost, edge.cost); 
            }
        }

        cost -= maxEdgeCost;

        System.out.println(cost);

    }
}
