import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);  // 도시 수
        int m = Integer.parseInt(s[1]);  // 도로 수
        int k = Integer.parseInt(s[2]);  // 면접관 수

        ArrayList<ArrayList<Node>> nodes = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }

        // 단방향 그래프 입력
        for (int i = 0; i < m; i++) {
            s = br.readLine().split(" ");
            int from = Integer.parseInt(s[0]);
            int to  = Integer.parseInt(s[1]);
            long cost = Long.parseLong(s[2]);

            nodes.get(to).add(new Node(from, cost)); // 면접관으로 향하는 방향만 저장
        }

        int[] place = new int[k];
        s = br.readLine().split(" ");
        for (int i = 0; i < s.length; i++) {
            place[i] = Integer.parseInt(s[i]);
        }

        // 다익스트라 알고리즘 수행
        long[] visited = new long[n + 1];
        Arrays.fill(visited, Long.MAX_VALUE);
        dijkstra(nodes, visited, place);

        // 최댓값 찾기
        long maxDistance = 0;
        int maxCity = 0;
        for (int i = 1; i <= n; i++) {
            if (visited[i] != Long.MAX_VALUE && visited[i] > maxDistance) {
                maxDistance = visited[i];
                maxCity = i;
            }
        }

        System.out.println(maxCity);
        System.out.println(maxDistance);
    }

    private static void dijkstra(ArrayList<ArrayList<Node>> nodes, long[] visited, int[] place) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(v -> v.cost));

        // 여러 면접관의 시작점 설정
        for (int p : place) {
            pq.offer(new Node(p, 0));
            visited[p] = 0;
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.cost > visited[node.to]) continue; // 이미 처리된 노드 무시

            for (Node no : nodes.get(node.to)) {
                if (visited[no.to] > visited[node.to] + no.cost) {
                    visited[no.to] = visited[node.to] + no.cost;
                    pq.offer(new Node(no.to, visited[no.to]));
                }
            }
        }
    }

    private static class Node {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
