import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int res = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Move> queue = new LinkedList<>();

    static class Move {
        int x;
        int y;
        int cnt;

        public Move(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N];

        for(int i=0; i<N; i++){
            String[] strings = br.readLine().split("");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(strings[j]);
            }
        }

        bfs();

        System.out.println(res);
    }
    static void bfs(){
        queue.add(new Move(0, 0, 0));
        for(int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE); // visited 배열을 최대값으로 초기화
        }
        visited[0][0] = 0;

        while(!queue.isEmpty()) {
            Move m = queue.poll();

            if (m.x == N - 1 && m.y == N - 1) {
                res = Math.min(m.cnt, res);
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + m.x;
                int ny = dy[i] + m.y;

                if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                    int nextCnt = m.cnt + (map[nx][ny] == 0 ? 1 : 0); // 검은 방일 경우에만 cnt 증가

                    // 현재 경로가 이전 경로보다 더 적은 검은 방을 통과하는 경우에만 방문
                    if (visited[nx][ny] > nextCnt) {
                        visited[nx][ny] = nextCnt;
                        queue.add(new Move(nx, ny, nextCnt));
                    }
                }
            }
        }
    }
}
