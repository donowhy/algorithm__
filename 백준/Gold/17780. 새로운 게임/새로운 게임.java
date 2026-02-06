import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int n, k;
    private static int[][] map; 
    private static List<Integer>[][] board; 
    private static List<Horse> horses = new ArrayList<>();

    private static final int[] dr = {0, 0, 0, -1, 1};
    private static final int[] dc = {0, 1, -1, 0, 0};

    private static class Horse {
        int id, r, c, dir;
        public Horse(int id, int r, int c, int dir) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        board = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; 
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            horses.add(new Horse(i, r, c, dir));
            board[r][c].add(i); 
        }

        System.out.println(solve());
    }

    private static int solve() {
        for (int turn = 1; turn <= 1000; turn++) {
            for (int i = 0; i < k; i++) {
                Horse cur = horses.get(i);

                if (board[cur.r][cur.c].get(0) != i) continue;

                int nr = cur.r + dr[cur.dir];
                int nc = cur.c + dc[cur.dir];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 2) {
                    cur.dir = reverseDir(cur.dir);
                    nr = cur.r + dr[cur.dir];
                    nc = cur.c + dc[cur.dir];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] == 2) continue;
                }

                if (move(cur.r, cur.c, nr, nc, map[nr][nc])) return turn;
            }
        }
        return -1;
    }

    private static boolean move(int r, int c, int nr, int nc, int color) {
        List<Integer> currentStack = board[r][c];

        if (color == 1) {
            Collections.reverse(currentStack);
        }

        for (int id : currentStack) {
            board[nr][nc].add(id);
            horses.get(id).r = nr;
            horses.get(id).c = nc;
        }

        currentStack.clear();

        return board[nr][nc].size() >= 4;
    }

    private static int reverseDir(int dir) {
        if (dir == 1) return 2;
        if (dir == 2) return 1;
        if (dir == 3) return 4;
        return 3;
    }
}