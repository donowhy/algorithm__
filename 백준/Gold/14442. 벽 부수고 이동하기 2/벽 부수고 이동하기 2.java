import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] map;
    static int[][][] visited;
    static ArrayDeque<point> dq = new ArrayDeque<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class point {
        int x;
        int y;
        int k;
        public point(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new int[N][M][K+1];  // considering the number of breaks

        for (int i=0; i<N; i++){
            String line = br.readLine();
            for (int j=0; j<M; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        visited[0][0][0] = 1;  // starting point
        dq.add(new point(0, 0, 0));
        System.out.println(bfs());
    }

    static int bfs() {

        while (!dq.isEmpty()){
            point p = dq.poll();

            if (p.x == N-1 && p.y == M-1)  // if it reaches the end point
                return visited[p.x][p.y][p.k];

            for (int i=0; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(validLogic(nx, ny)){
                    if (map[nx][ny] == 1 && p.k < K && visited[nx][ny][p.k+1] == 0){
                        visited[nx][ny][p.k+1] = visited[p.x][p.y][p.k] + 1;
                        dq.add(new point(nx, ny, p.k + 1));
                    }
                    else if (map[nx][ny] == 0 && visited[nx][ny][p.k] == 0){
                        visited[nx][ny][p.k] = visited[p.x][p.y][p.k] + 1;
                        dq.add(new point(nx, ny, p.k));
                    }
                }
            }
        }
        return -1; // if it can't reach the end point
    }

    static boolean validLogic(int x, int y){
        return 0<= x && x <N && 0<= y && y < M;
    }
}
