import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static long N, P, Q;
    static HashMap<Long, Long> dp = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());

        dp.put(0L, 1L); // base case

        System.out.println(getValue(N));
    }

    public static long getValue(long n) {
        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        // We're using a loop instead of recursion
        while (!dp.containsKey(n)) {
            long val = getValue(n / P) + getValue(n / Q);
            dp.put(n, val);
        }

        return dp.get(n);
    }
}
