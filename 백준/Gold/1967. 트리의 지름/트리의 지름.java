import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    static List<List<Node>> tree = new ArrayList<>();
    static boolean[] visited;
    static int maxDistance = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree.get(u).add(new Node(v, weight));
            tree.get(v).add(new Node(u, weight));
        }

        // 첫 번째 DFS 실행
        dfs(1, 0);

        // 두 번째 DFS 실행
        Arrays.fill(visited, false);
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    static void dfs(int node, int distance) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        for (Node next : tree.get(node)) {
            if (!visited[next.end]) {
                dfs(next.end, distance + next.weight);
            }
        }
    }
}
