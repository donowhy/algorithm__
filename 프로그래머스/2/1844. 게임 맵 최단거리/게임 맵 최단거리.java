import java.util.*;

class Solution {
    class Node {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};

    public int solution(int[][] maps) {
        return findPath(0, 0, maps);
    }

    private int findPath(int startX, int startY, int[][] maps) {
        int rows = maps.length;
        int columns = maps[0].length;

        boolean[][] visited = new boolean[rows][columns];
        ArrayDeque<Node> queue = new ArrayDeque<>();

        queue.offer(new Node(startX, startY, 1));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // 상대 팀 진영 도착
            if (current.x == rows - 1 &&
                current.y == columns - 1) {
                return current.cost;
            }

            for (int direction = 0; direction < 4; direction++) {
                int nextX = current.x + dx[direction];
                int nextY = current.y + dy[direction];

                if (isValid(nextX, nextY, maps, visited)) {
                    visited[nextX][nextY] = true;
                    queue.offer(
                        new Node(nextX, nextY, current.cost + 1)
                    );
                }
            }
        }

        return -1;
    }

    private boolean isValid(
        int x,
        int y,
        int[][] maps,
        boolean[][] visited
    ) {
        return 0 <= x && x < maps.length
            && 0 <= y && y < maps[0].length
            && maps[x][y] == 1
            && !visited[x][y];
    }
}