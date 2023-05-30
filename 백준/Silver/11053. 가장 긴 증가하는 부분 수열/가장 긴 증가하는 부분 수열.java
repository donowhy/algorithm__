
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] res, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        res = new int[N];
        dp = new int[N];

        for (int i=0; i<N; i++){
            res[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        for(int i=1; i<N; i++){
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if(res[i] > res[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for (int value : dp) {
            if (max < value) {
                max = value;
            }
        }
        System.out.println(max);
    }
}
