import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int n, m, x, y, k;
    private static int[][] map;
    private static int[] dx = {0, 0, 0, -1, 1};
    private static int[] dy = {0, 1, -1, 0, 0};
    private static int[] dice = new int[7];

    private static class P {
        int x, y;
        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        P p = new P(x, y);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] orderList = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Moving moving = new Moving(); 

        for(int i = 0; i < k; i++) {
            int order = orderList[i];

            int nx = p.x + dx[order];
            int ny = p.y + dy[order];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }

            p.x = nx;
            p.y = ny;

            switch (order) {
                case 1: moving.east(dice); break;
                case 2: moving.west(dice); break;
                case 3: moving.north(dice); break;
                case 4: moving.south(dice); break;
            }

            paste(p);
            System.out.println(dice[1]);
        }
    }

    public static class Moving {
        public void east(int[] dice) {
            int temp = dice[6];
            dice[6] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = temp;
        }

        public void west(int[] dice) {
            int temp = dice[6];
            dice[6] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = temp;
        }

        public void north(int[] dice) {
            int temp = dice[6];
            dice[6] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = temp;
        }

        public void south(int[] dice) {
            int temp = dice[6];
            dice[6] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[2];
            dice[2] = temp;
        }
    }

    private static void paste(P p)  {
        if(map[p.x][p.y] == 0) {
            map[p.x][p.y] = dice[6]; 
        } else {
            dice[6] = map[p.x][p.y]; 
            map[p.x][p.y] = 0;       
        }
    }
}