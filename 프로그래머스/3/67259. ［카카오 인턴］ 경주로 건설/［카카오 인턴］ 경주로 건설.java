import java.util.*;

class Solution {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    int N;
    int[][][] visited;

    static class Node {
        int x, y, cost, dir;

        public Node(int x, int y, int cost, int dir) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        N = board.length;
        visited = new int[N][N][4];
        return bfs(board);
    }
    
    private int bfs(int[][] board) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, -1));
        int answer = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == N-1 && now.y == N-1) {
                answer = Math.min(answer, now.cost);
            }

            for(int dir=0; dir<4; dir++){
                int nx = now.x + dx[dir];
                int ny = now.y + dy[dir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1) continue;

                int nextCost = now.cost;
                if(now.dir == -1 || now.dir == dir) nextCost += 100;
                else nextCost += 600;

                if(visited[nx][ny][dir] == 0 || board[nx][ny] >= nextCost) {
                    q.add(new Node(nx, ny, nextCost, dir));
                    visited[nx][ny][dir] = 1;
                    board[nx][ny] = nextCost;
                }
            }
        }

        return answer;
    }
}
