import java.util.*;

class Solution {
    int[][] maze;
    int n, m;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};
    
    boolean[][] rVisited, bVisited;
    int[] redEnd, blueEnd;
    int answer = Integer.MAX_VALUE;

    public int solution(int[][] maze) {
        this.maze = maze;
        this.n = maze.length;
        this.m = maze[0].length;
        rVisited = new boolean[n][m];
        bVisited = new boolean[n][m];

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) { rx = i; ry = j; }
                else if (maze[i][j] == 2) { bx = i; by = j; }
                else if (maze[i][j] == 3) redEnd = new int[]{i, j};
                else if (maze[i][j] == 4) blueEnd = new int[]{i, j};
            }
        }

        // 시작 위치 방문 처리
        rVisited[rx][ry] = true;
        bVisited[bx][by] = true;

        backtracking(rx, ry, bx, by, 0);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void backtracking(int rx, int ry, int bx, int by, int count) {
        // 이미 찾은 최소 단계보다 많이 왔다면 가지치기
        if (count >= answer) return;

        boolean rFinished = (rx == redEnd[0] && ry == redEnd[1]);
        boolean bFinished = (bx == blueEnd[0] && by == blueEnd[1]);

        // 둘 다 도착 성공
        if (rFinished && bFinished) {
            answer = Math.min(answer, count);
            return;
        }

        // 수레 이동 시도 (도착한 수레는 1번만 실행, 미도착은 4방향 실행)
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                
                // 빨간 수레 다음 좌표 (도착했으면 고정, 아니면 이동)
                int nrx = rFinished ? rx : rx + dx[i];
                int nry = rFinished ? ry : ry + dy[i];

                // 파란 수레 다음 좌표 (도착했으면 고정, 아니면 이동)
                int nbx = bFinished ? bx : bx + dx[j];
                int nby = bFinished ? by : by + dy[j];

                if (!canMove(rx, ry, nrx, nry, bx, by, nbx, nby)) continue;

                // 방문 처리 (이미 도착한 수레는 방문 처리를 유지하므로 미도착일 때만 수행)
                if (!rFinished) rVisited[nrx][nry] = true;
                if (!bFinished) bVisited[nbx][nby] = true;

                backtracking(nrx, nry, nbx, nby, count + 1);

                // 방문 해제 (Backtracking)
                if (!rFinished) rVisited[nrx][nry] = false;
                if (!bFinished) bVisited[nbx][nby] = false;
                
                // 도착한 수레는 방향 전환이 의미 없으므로 반복문 조기 종료
                if (bFinished) break; 
            }
            if (rFinished) break;
        }
    }

    private boolean canMove(int rx, int ry, int nrx, int nry, int bx, int by, int nbx, int nby) {
        // 1. 범위 및 벽 체크
        if (nrx < 0 || nrx >= n || nry < 0 || nry >= m || nbx < 0 || nbx >= n || nby < 0 || nby >= m) return false;
        if (maze[nrx][nry] == 5 || maze[nbx][nby] == 5) return false;

        // 2. 이미 도착한 게 아닌데 방문했던 곳이라면 불가
        if (!(rx == redEnd[0] && ry == redEnd[1]) && rVisited[nrx][nry]) return false;
        if (!(bx == blueEnd[0] && by == blueEnd[1]) && bVisited[nbx][nby]) return false;

        // 3. 두 수레가 같은 칸에 위치 불가
        if (nrx == nbx && nry == nby) return false;

        // 4. 두 수레가 교차(Swap) 불가
        if (nrx == bx && nry == by && nbx == rx && nby == ry) return false;

        return true;
    }
}