import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Point> q = new LinkedList<>();
    static int inn = 0;
    static int res = 0;
    static boolean[][] check;
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        check = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] != 0 && !check[i][j]) {
                    inn ++ ;
                    res = Math.max(bfs(q, check, map, i, j), res);
                }
            }
        }
        System.out.println(inn);
        System.out.println(res);
    }

    private static int bfs(Queue<Point> q, boolean[][] check, int[][] map, int x, int y){
        q.add(new Point(x, y));
        check[x][y] = true;
        int cnt = 1; // 시작점을 이미 찾았으므로 초기값을 1로 설정

        while (!q.isEmpty()){
            Point p = q.poll();

            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(0 <= nx && nx < N && 0 <= ny && ny < M && !check[nx][ny] && map[nx][ny] == 1){
                    q.add(new Point(nx, ny));
                    check[nx][ny] = true; // 방문한 점으로 표시
                    cnt += 1;
                }
            }

        }
        return cnt;
    }
}
