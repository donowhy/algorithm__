import java.io.*;

public class Main {
    static int tc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc = Integer.parseInt(br.readLine());
        long[] dp = new long[102];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;
        for (int i = 6; i < 102; i++) {
            dp[i] = dp[i - 2] + dp[i - 3];
        }
        for (int i = 0; i < tc; i++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num]);
        }
    }
}
