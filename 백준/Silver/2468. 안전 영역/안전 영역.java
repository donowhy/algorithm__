import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
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
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int max = 0;

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int k = Integer.parseInt(st.nextToken());
                map[i][j] = k;
                max = Math.max(k, max);
            }
        }
        int res = 0;
        for(int z=0; z<=max; z++){
            check = new int[N][N];
            int cnt = 0;
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if (map[i][j] > z && check[i][j] != 1) {
                        cnt += 1;
                        bfs(i, j, z);
                    }
                }
            }
            res = Math.max(res, cnt);
        }
        System.out.println(res);
    }

    static void bfs(int i, int j, int z) {
        Deque<Point> q = new LinkedList<>();
        q.add(new Point(i, j));
        check[i][j] = 1;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int k=0; k<4; k++){
                int nx = p.x + dx[k];
                int ny = p.y + dy[k];

                if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > z && check[nx][ny] != 1){
                    check[nx][ny] = 1;
                    q.add(new Point(nx, ny));
                }
            }
        }
    }
}
