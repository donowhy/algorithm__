import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 배열의 길이
        int[] arr = new int[N + 1]; // 입력 배열 (1-indexed)

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // DP 테이블 초기화
        boolean[][] dp = new boolean[N + 1][N + 1];

        // 길이 1인 구간은 항상 팰린드롬
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 길이 2인 구간 판별
        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        // 길이 3 이상인 구간 판별
        for (int length = 3; length <= N; length++) {
            for (int start = 1; start <= N - length + 1; start++) {
                int end = start + length - 1;
                if (arr[start] == arr[end] && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                }
            }
        }

        // 쿼리 처리
        int M = Integer.parseInt(br.readLine()); // 쿼리 개수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(dp[S][E] ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }
}
