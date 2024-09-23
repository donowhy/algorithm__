import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        visited = new boolean[n + 1];

        // Initialize the graph
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // Read the connections and build the graph
        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(" ");
            int from = Integer.parseInt(s[0]);
            int to = Integer.parseInt(s[1]);

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // Start BFS from node 1
        int result = bfs(1);
        System.out.println(result);
    }

    private static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int depth = current[1];

            // If depth is more than 2, stop processing
            if (depth >= 2) continue;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, depth + 1});
                    count++;
                }
            }
        }

        return count;
    }
}
