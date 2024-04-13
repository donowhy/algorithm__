import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            String cash = br.readLine();
            arr[i] = Integer.parseInt(cash);
        }

        int[] dp = new int[k + 1];

        for (int i = 1; i <= k; i++) {
            dp[i] = Integer.MAX_VALUE - 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = arr[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
            }
        }

        int result = dp[k] == Integer.MAX_VALUE - 1 ? -1 : dp[k];
        System.out.println(result);
    }
}