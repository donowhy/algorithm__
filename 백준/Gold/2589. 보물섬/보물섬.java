import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // L : land, W : sea
    //
    private static int n, m;
    private static char[][] map;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'W') continue;
                bfs(i, j);
            }
        }
        System.out.println(result);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));

        int[][] dist = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) dist[i][j] = -1;
        }

        dist[x][y] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(int i=0; i<4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == 'W' || dist[nx][ny] != -1) continue;

                dist[nx][ny] = dist[current.x][current.y] + 1;
                q.offer(new Node(nx, ny));

                result = Math.max(result, dist[nx][ny]);
            }
        }
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
