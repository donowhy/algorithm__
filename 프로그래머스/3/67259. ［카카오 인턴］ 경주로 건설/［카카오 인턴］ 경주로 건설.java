import java.util.*;

class Solution {

    public int[] dx = {1, 0, -1, 0};
    public int[] dy = {0, 1, 0, -1};

    public class Node {
        int x;
        int y;
        int turn;
        int straight;
        int type;

        public Node(int x, int y, int turn, int straight, int type) {
            this.x = x;
            this.y = y;
            this.turn = turn;
            this.straight = straight;
            this.type = type;
        }

        public int getCost() {
            return straight * 100 + turn * 500;
        }
    }

    public int solution(int[][] board) {
        return bfs(board);
    }

    public int bfs(int[][] b) {
        int tx = b.length;
        int ty = b[0].length;

        // cost[x][y][방향]
        int[][][] cost = new int[tx][ty][4];

        for (int i = 0; i < tx; i++) {
            for (int j = 0; j < ty; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }

        ArrayDeque<Node> queue = new ArrayDeque<>();

        // 시작점은 방향이 없음
        queue.offer(new Node(0, 0, 0, 0, -1));

        int minCost = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node n = queue.poll();

            int currentCost = n.getCost();

            if (n.x == tx - 1 && n.y == ty - 1) {
                minCost = Math.min(minCost, currentCost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];

                // 반드시 범위 검사를 먼저 해야 함
                if (nx < 0 || nx >= tx || ny < 0 || ny >= ty) {
                    continue;
                }

                if (b[nx][ny] == 1) {
                    continue;
                }

                int nextTurn = n.turn;
                int nextStraight = n.straight + 1;

                // 시작점이 아니고 방향이 바뀌면 코너 추가
                if (n.type != -1 && n.type != i) {
                    nextTurn++;
                }

                int nextCost =
                    nextStraight * 100 + nextTurn * 500;

                // 같은 위치, 같은 방향으로 더 싼 비용일 때만 이동
                if (nextCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = nextCost;

                    queue.offer(
                        new Node(
                            nx,
                            ny,
                            nextTurn,
                            nextStraight,
                            i
                        )
                    );
                }
            }
        }

        return minCost;
    }
}