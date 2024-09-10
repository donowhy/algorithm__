import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int[][] dp;
    private static int n, m;
    private static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);

        map = new int[n][m];
        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] v = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(v[j]);
                dp[i][j] = -1; // 아직 방문하지 않은 좌표는 -1로 초기화
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) {
            return 1; // 목표 지점에 도달하면 경로를 하나 찾았다는 의미
        }

        if (dp[x][y] != -1) {
            return dp[x][y]; // 이미 계산된 경로의 수가 있으면 그것을 반환
        }

        dp[x][y] = 0; // 경로의 수를 0으로 초기화하고 탐색 시작

        for (int i = 0; i < 4; i++) {
            int nx = x + d[i][0];
            int ny = y + d[i][1];

            if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny); // 더 낮은 곳으로만 이동할 수 있는 경우만 탐색
            }
        }

        return dp[x][y];
    }
}
