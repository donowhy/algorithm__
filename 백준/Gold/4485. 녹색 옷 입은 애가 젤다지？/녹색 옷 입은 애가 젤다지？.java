import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static class Locate implements Comparable<Locate>{
        int x;
        int y;
        int rupee;

        public Locate (int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Locate locate) {
            return Integer.compare(this.rupee, locate.rupee);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = 1;
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) return;

            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                String[] rupees = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(rupees[j]);
                }
            }

            int lostRupee = findLowestWay(0, 0, map, N);
            System.out.println("Problem " + testCase  + ": " + lostRupee);
            testCase++;
        }
    }

    private static int findLowestWay(int x, int y, int[][] map, int mapSize) {
        PriorityQueue<Locate> locates = new PriorityQueue<>();
        locates.add(new Locate(x, y, map[x][y]));
        int result = Integer.MAX_VALUE;
        boolean[][] check = new boolean[mapSize][mapSize];
        fillFalseInCheck(check);
        check[x][y] = true;

        while(!locates.isEmpty()) {
            Locate locate = locates.poll();

            if(locate.x == mapSize - 1 && locate.y == mapSize - 1 ) {
                result = Math.min(locate.rupee, result);
            }

            for (int i = 0; i < 4; i++) {
                int nextX = locate.x + dx[i];
                int nextY = locate.y + dy[i];

                if(nextX < 0 || nextX >= mapSize || nextY < 0 || nextY >= mapSize || check[nextX][nextY]) {
                    continue;
                }

                check[nextX][nextY] = true;
                locates.offer(new Locate(nextX, nextY, locate.rupee + map[nextX][nextY]));
            }
        }

        return result;
    }

    private static void fillFalseInCheck(boolean[][] check) {
        for (boolean[] fill : check) {
            Arrays.fill(fill, false);
        }
    }
}
