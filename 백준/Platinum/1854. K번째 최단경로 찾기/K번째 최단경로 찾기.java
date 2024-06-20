import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int cost, node;

        Node(int cost, int node) {
            this.cost = cost;
            this.node = node;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }

    public static void dijkstra(int start, List<List<Node>> graph, int[][] res, int k) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, start));
        res[start][0] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int cost = current.cost;
            int node = current.node;

            for (Node neighbor : graph.get(node)) {
                int newCost = cost + neighbor.cost;
                if (res[neighbor.node][k - 1] > newCost) {
                    res[neighbor.node][k - 1] = newCost;
                    Arrays.sort(res[neighbor.node]);
                    pq.add(new Node(newCost, neighbor.node));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        int inf = 1000000000;

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[][] res = new int[n + 1][k];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(res[i], inf);
        }

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int hour = scanner.nextInt();
            graph.get(start).add(new Node(hour, end));
        }

        dijkstra(1, graph, res, k);

        for (int i = 1; i <= n; i++) {
            if (res[i][k - 1] == inf) {
                System.out.println(-1);
            } else {
                System.out.println(res[i][k - 1]);
            }
        }

        scanner.close();
    }
}
