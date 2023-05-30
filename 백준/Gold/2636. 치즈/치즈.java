import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map, check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
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
        check = new int[N][M];


        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        int a = 0;
        while(true){
            for( int[] row: check){
                Arrays.fill(row,0);
            }
            int tmp = bfs();
            time += 1;
            if (tmp != 0) {
                a = tmp;
            }else{
                break;
            }
        }
        System.out.println(time - 1);
        System.out.println(a);


    }
    static int bfs(){
        Deque<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        check[0][0] = 1;
        int cnt = 0;
        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0; i<4; i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if( 0 <= nx && nx < N && 0 <= ny && ny < M && check[nx][ny] == 0 && check[nx][ny] != 2){
                    if(map[nx][ny] == 1){
                        check[nx][ny] = 2;
                        cnt += 1;
                        continue;
                    }
                    check[nx][ny] = 1;
                    q.add(new Point(nx, ny));
                }
            }

        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(check[i][j] == 2) {
                    map[i][j] = 0;
                }
            }
        }

 
        return cnt;
    }
}
