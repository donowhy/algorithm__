import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Main {
    static char[][] map = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Puyo {
        int x, y;

        Puyo(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean bfs(int x, int y, char color) {
        Queue<Puyo> queue = new LinkedList<>();
        ArrayList<Puyo> list = new ArrayList<>();
        queue.add(new Puyo(x, y));
        list.add(new Puyo(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Puyo p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && nx < 12 && ny >= 0 && ny < 6) {
                    if (map[nx][ny] == color && !visited[nx][ny]) {
                        queue.add(new Puyo(nx, ny));
                        list.add(new Puyo(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        if (list.size() >= 4) {
            for (Puyo p : list) {
                map[p.x][p.y] = '.';
            }
            return true;
        } else {
            return false;
        }
    }

    static void down() {
        for (int i = 0; i < 6; i++) {
            ArrayList<Character> list = new ArrayList<>();
            for (int j = 11; j >= 0; j--) {
                if (map[j][i] != '.') {
                    list.add(map[j][i]);
                    map[j][i] = '.';
                }
            }
            int idx = 11;
            for (char puyo : list) {
                map[idx--][i] = puyo;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int result = 0;

        while (true) {
            boolean check = false;
            visited = new boolean[12][6];
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i, j, map[i][j])) {
                            check = true;
                        }
                    }
                }
            }
            if (!check) {
                break;
            } else {
                down();
                result++;
            }
        }
        System.out.println(result);
    }
}
