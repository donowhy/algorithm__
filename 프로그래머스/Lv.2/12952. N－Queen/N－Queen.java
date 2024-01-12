import java.util.*;

public class Solution {

    public int solution(int n) {
        boolean[] cols = new boolean[n]; // 각 열에 퀸이 있는지 여부
        boolean[] diag1 = new boolean[2 * n - 1]; // 대각선 방향 1
        boolean[] diag2 = new boolean[2 * n - 1]; // 대각선 방향 2
        return countSolutions(0, n, cols, diag1, diag2);
    }

    private int countSolutions(int y, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (y == n) {
            // 모든 행에 퀸을 배치했다면, 해결책을 찾은 것임
            return 1;
        }

        int solutions = 0;
        for (int x = 0; x < n; x++) {
            if (!cols[x] && !diag1[x + y] && !diag2[x - y + n - 1]) {
                // 유효한 위치에 퀸을 배치
                cols[x] = diag1[x + y] = diag2[x - y + n - 1] = true;
                solutions += countSolutions(y + 1, n, cols, diag1, diag2);
                // 배치 취소 (백트래킹)
                cols[x] = diag1[x + y] = diag2[x - y + n - 1] = false;
            }
        }

        return solutions;
    }
}