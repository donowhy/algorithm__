import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][N];
        int islandNumber = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, islandNumber);
                    islandNumber++;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < islandNumber; i++) {
            min = Math.min(min, getMinDist(i));
        }

        System.out.println(min);
    }

    static void bfs(int x, int y, int islandNumber) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;
        map[x][y] = islandNumber;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }

                visited[nx][ny] = true;
                map[nx][ny] = islandNumber;
                q.offer(new Point(nx, ny));
            }
        }
    }

    static int getMinDist(int islandNumber) {
        Queue<Point> q = new LinkedList<>();
        dist = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == islandNumber) {
                    q.offer(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        int minDist = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                dist[nx][ny] = dist[cur.x][cur.y] + 1;

                if (map[nx][ny] != 0 && map[nx][ny] != islandNumber) {
                    minDist = Math.min(minDist, dist[cur.x][cur.y]);
                } else if (map[nx][ny] == 0) {
                    q.offer(new Point(nx, ny));
                }
            }
        }

        return minDist;
    }
}
