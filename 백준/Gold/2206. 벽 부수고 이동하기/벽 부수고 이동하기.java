import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[][][] escape;
    static int N, M;
    static Deque<Point> q = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    static class Point {
        int x;
        int y;
        int z;
        public Point (int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        escape = new int[N][M][2];

        for(int i=0; i<N; i++ ){
            String line = br.readLine();
            for(int j=0; j<M; j++ ){
                map[i][j] = line.charAt(j) - '0';
            }
        }
        for(int i=0; i<N; i++ ){
            for(int j=0; j<M; j++ ){
                for(int k=0; k<2; k++){
                    escape[i][j][k] = 0;
                }
            }
        }
        int res = bfs();
        System.out.println((res > -1) ? res + 1 : -1);

    }
    static int bfs(){
        q.add(new Point(0,0,0));

        while (!q.isEmpty()){
            Point p = q.poll();

            if (p.x == N - 1 && p.y == M - 1){
                return escape[p.x][p.y][p.z];
            }

            for(int i=0; i<4; i++ ){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(p.z == 0) {
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && escape[nx][ny][p.z] == 0 && map[nx][ny] == 0){
                        escape[nx][ny][p.z] = escape[p.x][p.y][p.z] + 1;
                        q.add(new Point(nx,ny,p.z));
                    } else if (0 <= nx && nx < N && 0 <= ny && ny < M && escape[nx][ny][p.z] == 0 && map[nx][ny] == 1){
                        escape[nx][ny][1] = escape[p.x][p.y][p.z] + 1;
                        q.add(new Point(nx,ny,1));
                    }
                }else {
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && escape[nx][ny][p.z] == 0 && map[nx][ny] == 0) {
                        escape[nx][ny][1] = escape[p.x][p.y][p.z] + 1;
                        q.add(new Point(nx, ny, p.z));
                    }
                }

            }
        }
        return -1;
    }
}
