import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] grid, group, ans;
    static int[] groupSize;
    static int n, m, groupNo = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        grid = new int[n][m];
        group = new int[n][m];
        ans = new int[n][m];
        groupSize = new int[n * m + 1];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = row.charAt(j) - '0';
            }
        }

        // calculate size for each group
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && group[i][j] == 0) {
                    groupSize[groupNo] = dfs(i, j, groupNo);
                    groupNo++;
                }
            }
        }

        // calculate the answer
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    ans[i][j] = getAnswer(i, j) % 10;
                }
            }
        }

        // print the answer
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(ans[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static int dfs(int x, int y, int no) {
        int cnt = 1;
        group[x][y] = no;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 0 && group[nx][ny] == 0) {
                cnt += dfs(nx, ny, no);
            }
        }
        return cnt;
    }

    static int getAnswer(int x, int y) {
        Set<Integer> adjacentGroups = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 0) {
                adjacentGroups.add(group[nx][ny]);
            }
        }

        int ans = 1;
        for (int g : adjacentGroups) {
            ans += groupSize[g];
        }
        return ans;
    }
}
