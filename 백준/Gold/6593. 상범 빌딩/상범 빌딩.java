
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int L, R, C;
    static String[][][] map;
    static int[][][] visited;
    static int[] dx = {1, 0, -1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static Deque<Point> S = new LinkedList<>();

    static class Point{
        int z;
        int x;
        int y;

        public Point(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if(L == 0 && R == 0 && C == 0){
                break;
            }

            map = new String[L][R][C];
            visited = new int[L][R][C];


            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = Character.toString(line.charAt(k));
                        if (map[i][j][k].equals("S")) {
                            S.add(new Point(i, j, k));
                            visited[i][j][k] = 1;
                        }
                    }
                }
                String trap = br.readLine();
            }


//        for(int i=0; i<L; i++){
//            for(int j=0; j<R; j++){
//                for(int k=0; k<C; k++){
//                    System.out.print(map[i][j][k]);
//                }
//                System.out.println();
//            }
//            System.out.println();;
//        }

            int res = bfs();

            if (res == 0) {
                System.out.println("Trapped!");
            } else {
                System.out.println("Escaped in " + res + " minute(s).");
            }
        }

    }

    static int bfs(){
        Deque<Point> dq = new LinkedList<>();
        dq.add(S.poll());

        while(!dq.isEmpty()){
            Point p = dq.poll();

            for (int i=0; i<6; i++){
                int nz = p.z + dz[i];
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(valid(nz, nx, ny)){
                    if(map[nz][nx][ny].equals("E")){
                        return visited[p.z][p.x][p.y];
                    }
                    visited[nz][nx][ny] = visited[p.z][p.x][p.y] + 1;
                    dq.add(new Point(nz, nx, ny));
                }
            }
        }
        return 0;
    }

    static boolean valid(int nz, int nx, int ny){
        if(0 <= nx && nx < R && 0 <= nz && nz < L && 0 <= ny && ny < C && !map[nz][nx][ny].equals("#") && visited[nz][nx][ny] == 0){
            return true;
        }
        return false;
    }

}
