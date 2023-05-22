
import java.io.*;
import java.util.*;


public class Main {
    static char[][] box;
    static boolean[][] visited;
    static int N;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static Queue<Tomato> queue = new LinkedList<>();

    static class Tomato {
        int x;
        int y;

        public Tomato(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        box = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();

            for (int j = 0; j < N; j++) {
                box[i][j] = line.charAt(j);
            }
        }
        visited = new boolean[N][N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                    cnt += 1;
                }
            }
        }

        int ct = 0;
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (box[i][j] == 'G'){
                    box[i][j] = 'R';
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    bfs(i, j);
                    ct += 1;
                }
            }
        }
        System.out.println(cnt + " " + ct);
    }
    public static void bfs(int ii, int jj){
        queue.offer(new Tomato(ii,jj));
        while(!queue.isEmpty()){
            Tomato tomato = queue.poll();
            visited[tomato.x][tomato.y] = true;

            for (int i = 0; i < 4; i++) {
                int nx = tomato.x + dx[i];
                int ny = tomato.y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    if (box[nx][ny] == box[ii][jj] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new Tomato(nx, ny));
                    }
                }
            }
        }
    }
}
