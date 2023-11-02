import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 200_000_000;
    static int n, m, start, end;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static int[] prev; // 경로를 추적하기 위한 배열

    static class Node implements Comparable<Node> {
        int vertex, weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        long distance = dijkstra(start, end);
        List<Integer> path = retrievePath(end);

        System.out.println(distance);
        System.out.println(path.size()); // 경로에 포함된 노드 수 출력
        for (int v : path) {
            System.out.print(v + " "); // 경로 출력
        }
        System.out.println();

        br.close();
    }

    static long dijkstra(int start, int end) {
        dist = new int[n + 1];
        prev = new int[n + 1]; // 경로 추적을 위한 배열
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.vertex]) continue;
            visited[current.vertex] = true;

            for (Node node : graph.get(current.vertex)) {
                if (!visited[node.vertex] && dist[node.vertex] > dist[current.vertex] + node.weight) {
                    dist[node.vertex] = dist[current.vertex] + node.weight;
                    prev[node.vertex] = current.vertex; // 경로 추적을 위한 이전 노드 저장
                    pq.offer(new Node(node.vertex, dist[node.vertex]));
                }
            }
        }

        return dist[end];
    }

    static List<Integer> retrievePath(int end) {
        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
