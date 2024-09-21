import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n, m;
    private static int[][] map;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        int size = (int) Math.pow(2,  n);

        map = new int[size][size];

        for (int i = 0; i < size; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < size; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }

        String[] tc = br.readLine().split(" ");

        for (int i = 0; i < tc.length; i++) {
            int l = Integer.parseInt(tc[i]);
            splitBlock(l);
        }

        int totalIce = calcTotalIce();
        int maxIceBlock = calcMaxIceBlock();

        System.out.println(totalIce);
        System.out.println(maxIceBlock);
    }

    private static void splitBlock(int l) {
        int inter = (int) Math.pow(2, l);
        for (int i = 0; i < map.length; i += inter) {
            for (int j = 0; j < map.length; j += inter) {
                turnToRight(i, j, inter);
            }
        }
        calcMap();
    }

    private static void turnToRight(int startX, int startY, int size) {
        int[][] temp = new int[size][size];
        
        // 90도 회전
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                temp[j][size - 1 - i] = map[startX + i][startY + j];
            }
        }

        // 회전된 결과를 원래 맵에 적용
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[startX + i][startY + j] = temp[i][j];
            }
        }
    }

    private static void calcMap() {
        int[][] newMap = new int[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] <= 0) continue;

                int adjacentIce = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx >= 0 && ny >= 0 && nx < map.length && ny < map.length && map[nx][ny] > 0) {
                        adjacentIce++;
                    }
                }

                if (adjacentIce < 3) {
                    newMap[i][j] = map[i][j] - 1;
                } else {
                    newMap[i][j] = map[i][j];
                }
            }
        }

        map = newMap;
    }

    private static int calcTotalIce() {
        int sum = 0;
        for (int[] row : map) {
            for (int ice : row) {
                sum += ice;
            }
        }
        return sum;
    }

    private static int calcMaxIceBlock() {
        boolean[][] visited = new boolean[map.length][map.length];
        int maxBlock = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    maxBlock = Math.max(maxBlock, dfs(i, j, visited));
                }
            }
        }

        return maxBlock;
    }

    private static int dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int blockSize = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < map.length && ny < map.length && map[nx][ny] > 0 && !visited[nx][ny]) {
                blockSize += dfs(nx, ny, visited);
            }
        }

        return blockSize;
    }
}
