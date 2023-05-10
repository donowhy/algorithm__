import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 빵집
public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static boolean dfs(int x, int y) {
        if (y == C - 1) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            if (nx >= 0 && nx < R && ny < C && map[nx][ny] != 'x' && !visited[nx][ny]) {
                visited[nx][ny] = true;
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }
        return false;
    }
}
