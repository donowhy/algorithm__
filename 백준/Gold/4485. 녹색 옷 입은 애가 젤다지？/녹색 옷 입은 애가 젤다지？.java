import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        while(true) {
            cnt ++;
            int n = Integer.parseInt(br.readLine());

            if(n == 0) break;

            int[][] map = new int[n][n];

            for(int i=0; i<n; i++) {
                String[] s = br.readLine().split(" ");
                for(int j=0; j<n; j++) {
                    map[i][j] = Integer.parseInt(s[j]);
                }
            }

            int go = go(map);
            System.out.printf("Problem %d: %d%n", cnt, go);
        }
    }

    private static int go(int[][] map) {
        int[][] dist = new int[map.length][map.length];
        for(int i=0; i<map.length; i++) {
            for(int j=0; j<map.length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int cost = node.cost;

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= map.length || ny < 0 || ny >= map.length) continue;
                if(dist[nx][ny] <= cost + map[nx][ny]) continue;

                dist[nx][ny] = cost + map[nx][ny];
                pq.offer(new Node(nx, ny, dist[nx][ny]));
            }
        }

        return dist[map.length-1][map.length-1];
    }

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static class Node implements Comparable<Node> {
        int x, y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
