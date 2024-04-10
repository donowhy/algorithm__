import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static boolean[][] check;
    private static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    private static int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = getList(br);

        int n = list.get(0);
        int m = list.get(1);

        int[][] map = new int[n][m];
        check = new boolean[n][m];
        ArrayList<Location> container = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> temp = getList(br);
            for (int j = 0; j < m; j++) {
                if(temp.get(j)==0) {
                    check[i][j] = true;
                } else{
                    container.add(new Location(i, j));
                }
                map[i][j] = temp.get(j);
            }
        }
        int result = 0;

        for (Location location : container) {
            if(!check[location.x][location.y]) {
                bfs(map, location.x, location.y, n, m);
                result++;
            }
        }

        System.out.println(result);
    }

    private static void bfs(int[][] map, int x, int y, int n, int m) {
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(x, y));

        while(!q.isEmpty()) {
            Location location = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = location.x + dx[i];
                int ny = location.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m || check[nx][ny] || map[nx][ny] == 0) {
                    continue;
                }
                check[nx][ny] = true;
                q.offer(new Location(nx, ny));
            }
        }
    }

    private static List<Integer> getList(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    static class Location{
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
