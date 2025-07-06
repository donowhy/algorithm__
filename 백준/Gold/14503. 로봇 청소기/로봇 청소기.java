import java.io.*;

public class Main {

    private static int[] dx = {-1, 0, 1, 0}; 
    private static int[] dy = {0, 1, 0, -1};

    private static int result = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        s = br.readLine().split(" ");

        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]);

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        moving(x, y, d, map);

        System.out.println(result);
    }

    private static void moving(int x, int y, int d, int[][] map) {
        while (true) {
            if (map[x][y] == 0) {
                result++;
                map[x][y] = 2;
            }

            boolean moved = findMap(x, y, map);
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < map.length && ny >= 0 && ny < map[0].length && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                int bx = x - dx[d];
                int by = y - dy[d];

                if (bx >= 0 && bx < map.length && by >= 0 && by < map[0].length && map[bx][by] != 1) {
                    x = bx;
                    y = by;
                } else {
                    break;
                }
            }
        }
    }


    private static boolean findMap(int x, int y, int[][] map) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length) {
                continue;
            }

            if (map[nx][ny] == 0) {
                return true;
            }
        }
        return false;
    }
}
