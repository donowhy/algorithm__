import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    private static final int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] hy = {-1, 1, -2, 2, -2, 2, -1, 1};

    private static int k, w, h;
    private static int[][] map;

    private static boolean[][][] visited;

    private static class Monkey {
        int x, y, skill, cost;
        public Monkey (int x, int y, int skill, int cost) {
            this.x = x;
            this.y = y;
            this.skill = skill; 
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];

        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[k + 1][h][w];

        System.out.println(bfs());
    }

    private static int bfs() {
        Deque<Monkey> q = new ArrayDeque<>();
        q.add(new Monkey(0, 0, k, 0));
        visited[k][0][0] = true;

        while(!q.isEmpty()) {
            Monkey cur = q.poll();

            if (cur.x == h - 1 && cur.y == w - 1) {
                return cur.cost;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(canGo(nx, ny) && !visited[cur.skill][nx][ny]) {
                    visited[cur.skill][nx][ny] = true;
                    q.add(new Monkey(nx, ny, cur.skill, cur.cost + 1));
                }
            }

            if(cur.skill > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + hx[i];
                    int ny = cur.y + hy[i];
                    int nextSkill = cur.skill - 1;

                    if(canGo(nx, ny) && !visited[nextSkill][nx][ny]) {
                        visited[nextSkill][nx][ny] = true;
                        q.add(new Monkey(nx, ny, nextSkill, cur.cost + 1));
                    }
                }
            }
        }

        return -1;
    }
    
    private static boolean canGo(int x, int y) {
        if (x < 0 || y < 0 || x >= h || y >= w) return false;
        if (map[x][y] == 1) return false;
        return true;
    }
}