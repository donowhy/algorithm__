import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Point> cheeseList;
    static int cheeseCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        map = new int[N][M];
        cheeseList = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String[] values = br.readLine().split(" ");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(values[j]);
                if(map[i][j] == 1) {
                    cheeseList.add(new Point(i, j));
                    cheeseCnt += 1;
                }
            }
        }

        int time = 0;

        while(cheeseCnt != 0) {
            time++;
            visited = new boolean[N][M];
            dfs(0, 0); // 외부 공기 찾기
            // bfs();
            melting(); // 치즈 녹이기
        }

        System.out.println(time);
    }

    private static void melting() {
        for(int i = 0; i < cheeseList.size(); i++) {
            int x = cheeseList.get(i).x;
            int y = cheeseList.get(i).y;
            int cnt = 0;

            for(int j = 0; j < 4; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];

                if(map[nx][ny] == 2) {
                    cnt++;
                }
            }

            if(cnt >= 2) { // 외부 공기와 2변 이상 접촉한 경우
                map[x][y] = 0;
                cheeseCnt--;
                cheeseList.remove(i);
                i--;
            }
        }
    }


    // dfs로 외부와 접촉한 공기 2로 표시
    private static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = 2; // 외부 공기라는 의미로 2로 바꿔줌

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (visited[nx][ny] || map[nx][ny] == 1) continue; // 외내부 공기 판별을 위해 치즈인 경우도 pass

            dfs(nx, ny); // 공기인 경우만 dfs 수행
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}