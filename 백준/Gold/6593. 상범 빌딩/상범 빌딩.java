import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static class Point {
        int x, y, z;
        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static int L, R, C;
    static char[][][] map;
    static boolean[][][] visited;
    static int[] dx = { -1, 1, 0, 0, 0, 0 };
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, -1, 1 };
    static Point start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];
            visited = new boolean[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String str = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = str.charAt(k);
                        if (map[i][j][k] == 'S') start = new Point(i, j, k);
                        if (map[i][j][k] == 'E') end = new Point(i, j, k);
                    }
                }
                br.readLine();
            }

            int result = bfs();

            System.out.println(result == -1 ? "Trapped!" : "Escaped in " + result + " minute(s).");
        }
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        visited[start.x][start.y][start.z] = true;
        queue.add(start);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                Point p = queue.poll();
                if (p.x == end.x && p.y == end.y && p.z == end.z) return count;

                for (int i = 0; i < 6; i++) {
                    int nx = p.x + dx[i];
                    int ny = p.y + dy[i];
                    int nz = p.z + dz[i];

                    if (nx < 0 || ny < 0 || nz < 0 || nx >= L || ny >= R || nz >= C) continue;
                    if (visited[nx][ny][nz] || map[nx][ny][nz] == '#') continue;

                    visited[nx][ny][nz] = true;
                    queue.add(new Point(nx, ny, nz));
                }
            }
            count++;
        }
        return -1;
    }
}
