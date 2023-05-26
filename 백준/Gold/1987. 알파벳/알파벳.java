import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[] visited;
    static int max = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[26];  // ASCII 'A' ~ 'Z'

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        visited[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(max);
    }

    static void dfs(int x, int y, int count) {
        max = Math.max(max, count);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                int next = map[nx][ny] - 'A';
                if (!visited[next]) {
                    visited[next] = true;
                    dfs(nx, ny, count + 1);
                    visited[next] = false;  // backtracking
                }
            }
        }
    }
}
