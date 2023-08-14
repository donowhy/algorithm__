import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        long[] remains = new long[M];
        
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum = (sum + nums[i]) % M;
            remains[sum]++;
        }

        long count = remains[0]; 
        for (int i = 0; i < M; i++) {
            if (remains[i] >= 2) {
                count += (remains[i] * (remains[i] - 1)) / 2;
            }
        }

        System.out.println(count);
    }
}
