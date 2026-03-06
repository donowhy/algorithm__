import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int n, k, l;
    private static int[][] map;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static class Snake {
        int x, y, state;
        public Snake(int x, int y, int state) {
            this.x = x;
            this.y = y;
            this.state = state;
        }
    }

    private static class Loc {
        int x, y;
        public Loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n][n];

        for(int i=0; i<k; i++) {
            String[] s = br.readLine().split(" ");
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[1]);
            map[x - 1][y - 1] = 1;
        }

        l = Integer.parseInt(br.readLine());
        Map<Integer, String> moves = new HashMap<>();
        for(int i=0; i<l; i++) {
            String[] s = br.readLine().split(" ");
            moves.put(Integer.parseInt(s[0]), s[1]);
        }

        Snake snake = new Snake(0, 0, 3); 
        ArrayDeque<Loc> locList = new ArrayDeque<>();
        locList.add(new Loc(0, 0)); 
        map[0][0] = 2;

        int time = 0; 

        while (true) {
            time++; 

            int nx = snake.x + dx[snake.state];
            int ny = snake.y + dy[snake.state];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] == 2) {
                break;
            }

            locList.add(new Loc(nx, ny));

            if (map[nx][ny] == 1) {
                map[nx][ny] = 2;
            } else {
                map[nx][ny] = 2;
                Loc tail = locList.poll(); 
                map[tail.x][tail.y] = 0;   
            }

            snake.x = nx;
            snake.y = ny;

            if (moves.containsKey(time)) {
                String dir = moves.get(time);
                if (dir.equals("L")) {
                    snake.state = (snake.state + 1) % 4;
                } else {
                    snake.state = (snake.state + 3) % 4; 
                }
            }
        }

        System.out.println(time);
    }
}