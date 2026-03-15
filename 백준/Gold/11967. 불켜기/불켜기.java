import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m;

    private static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static ArrayList<Node>[][] switches;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        switches = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            switches[x][y].add(new Node(a, b));
        }

        bfs(1, 1);
    }

    private static void bfs(int startX, int startY) {
        boolean[][] light = new boolean[n + 1][n + 1];
        boolean[][] visited = new boolean[n + 1][n + 1];
        ArrayDeque<Node> q = new ArrayDeque<>();

        light[startX][startY] = true;
        visited[startX][startY] = true;
        q.offer(new Node(startX, startY));

        int ans = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Node next : switches[cur.x][cur.y]) {
                if (!light[next.x][next.y]) {
                    light[next.x][next.y] = true;
                    ans++;

                    for (int i = 0; i < 4; i++) {
                        int nx = next.x + dx[i];
                        int ny = next.y + dy[i];

                        if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                            if (visited[nx][ny]) {
                                visited[next.x][next.y] = true;
                                q.offer(new Node(next.x, next.y));
                                break;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
                    if (light[nx][ny] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.offer(new Node(nx, ny));
                    }
                }
            }
        }

        System.out.println(ans);
    }
}