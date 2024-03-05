import java.util.*;

class Solution {
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[102][102]; // 확대하기 위해 크기 조정
        boolean[][] check = new boolean[102][102];
        for (int[] rect : rectangle) {
            // 확대하여 그리기
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;
            
            // 경계선 그리기
            for (int i = x1; i <= x2; i++) {
                map[i][y1] = map[i][y2] = 1; // 상하
            }
            for (int i = y1; i <= y2; i++) {
                map[x1][i] = map[x2][i] = 1; // 좌우
            }
        }

        // 경계선을 제외한 내부 지우기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int i = x1 + 1; i < x2; i++) {
                for (int j = y1 + 1; j < y2; j++) {
                    map[i][j] = 0;
                }
            }
        }

        // BFS 시작
        int result = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, map, check);
        return result / 2; // 길이를 확대했으므로, 실제 거리는 절반
    }

    static int bfs(int x, int y, int targetX, int targetY, int[][] map, boolean[][] check) {
        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(x, y, 0));
        check[x][y] = true;

        while (!q.isEmpty()) {
            Location l = q.poll();
            if (l.x == targetX && l.y == targetY) {
                return l.cost;
            }

            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, 1, 0, -1};
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + l.x;
                int ny = dy[i] + l.y;

                if (nx >= 0 && nx < 102 && ny >= 0 && ny < 102 && map[nx][ny] == 1 && !check[nx][ny]) {
                    check[nx][ny] = true;
                    q.offer(new Location(nx, ny, l.cost + 1));
                }
            }
        }
        return -1; // 경로를 찾을 수 없는 경우
    }

    static class Location {
        int x;
        int y;
        int cost;
        public Location(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
