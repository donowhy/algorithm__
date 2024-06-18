import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        List<Edge> edges = new ArrayList<>();
        
        // 우물 비용 추가 (0번 노드와 연결된 간선)
        for (int i = 1; i <= N; i++) {
            int cost = Integer.parseInt(br.readLine());
            edges.add(new Edge(0, i, cost));
        }
        
        // 논 사이의 간선 추가
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (i != j) {
                    edges.add(new Edge(i, j, cost));
                }
            }
        }
        
        // 간선 가중치 순으로 정렬
        Collections.sort(edges);
        
        // 크루스칼 알고리즘을 이용하여 MST 구성
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        
        int mstCost = 0;
        int edgeCount = 0;
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstCost += edge.weight;
                edgeCount++;
                if (edgeCount == N) break;  // N개의 간선을 찾으면 종료
            }
        }
        
        System.out.println(mstCost);
    }
    
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
