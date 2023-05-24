import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    static char[][] map;
    static int[][] fire_sp, man_run;
    static int R, C;
    static Deque<Point> fire = new LinkedList<>();
    static Deque<Point> man = new LinkedList<>();
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

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
        String[] dimensions = br.readLine().split(" ");
        R = Integer.parseInt(dimensions[0]);
        C = Integer.parseInt(dimensions[1]);
        map = new char[R][C];
        fire_sp = new int[R][C];
        man_run = new int[R][C];

        // -1로 초기화
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                fire_sp[i][j] = -1;
                man_run[i][j] = -1;
            }
        }

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    man.add(new Point(i, j));
                    man_run[i][j] = 0;
                }
                if (map[i][j] == 'F') {
                    fire.add(new Point(i, j));
                    fire_sp[i][j] = 0;
                }
            }
        }
        System.out.println(bfs());
    }

    static public Object bfs() {
        while (!fire.isEmpty()) {
            Point point = fire.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (0 <= nx && nx < R && 0 <= ny && ny < C && map[nx][ny] != '#' && fire_sp[nx][ny] == -1) {
                    fire_sp[nx][ny] = fire_sp[point.x][point.y] + 1;
                    fire.add(new Point(nx, ny));
                }
            }
        }
        while (!man.isEmpty()) {
            Point point = man.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || nx >= R || ny < 0 || ny >= C){
                    return man_run[point.x][point.y] + 1;
                }

                if (0 <= nx && nx < R && 0 <= ny && ny < C && map[nx][ny] != '#' && man_run[nx][ny] == -1 && (fire_sp[nx][ny] == -1 || fire_sp[nx][ny] > man_run[point.x][point.y] + 1)) {
                    man_run[nx][ny] = man_run[point.x][point.y] + 1;
                    man.add(new Point(nx, ny));
                }
            }
        }
        return "IMPOSSIBLE";
    }
}
