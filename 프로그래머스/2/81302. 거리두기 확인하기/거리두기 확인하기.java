import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    static class Locate {
        int x, y, dist;

        Locate(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public int[] solution(String[][] places) {
        int[] results = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            results[i] = isSafe(places[i]) ? 1 : 0;
        }

        return results;
    }

    private boolean isSafe(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(place, new Locate(i, j, 0))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean bfs(String[] place, Locate start) {
        Queue<Locate> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        queue.add(start);
        visited[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Locate current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny]) {
                    continue;
                }

                if (current.dist == 2) {
                    continue; // Stop if distance is 2
                }

                if (place[nx].charAt(ny) == 'X') {
                    continue; // Skip if there's a partition
                }

                if (place[nx].charAt(ny) == 'P') {
                    return false; // Fail if another 'P' is found within a distance of 2 without a partition
                }

                visited[nx][ny] = true;
                queue.add(new Locate(nx, ny, current.dist + 1));
            }
        }

        return true;
    }
}