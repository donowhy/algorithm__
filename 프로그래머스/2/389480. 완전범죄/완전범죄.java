class Solution {
    public int solution(int[][] info, int n, int m) {
        final int L = info.length;
        final int INF = 1_000_000_000;

        int[][] dp = new int[L][m];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < m; j++) dp[i][j] = INF;
        }

        // base
        dp[0][0] = Math.min(dp[0][0], info[0][0]); // 돈 냄
        if (info[0][1] < m) dp[0][info[0][1]] = 0;  // 돈 안 냄(피로 증가)

        // 일반 케이스
        for (int i = 1; i < L; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i-1][j] == INF) continue; // 불가능 상태 skip
                // 돈 냄: 피로 유지, 비용 추가
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + info[i][0]);
                // 돈 안 냄: 피로 증가, 비용 추가 없음
                int nj = j + info[i][1];
                if (nj < m) dp[i][nj] = Math.min(dp[i][nj], dp[i-1][j]);
            }
        }

        int answer = INF;
        for (int j = 0; j < m; j++) answer = Math.min(answer, dp[L-1][j]);

        // 예산 비교(문제 의도에 따라 < 또는 <=)
        return (answer < n) ? answer : -1;
    }
}
