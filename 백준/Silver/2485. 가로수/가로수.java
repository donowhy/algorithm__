import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int[] arr;
    static int[] diffs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        diffs = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diffs[i] = arr[i + 1] - arr[i];
        }

        int gcd = diffs[0];
        for (int i = 1; i < n - 1; i++) {
            gcd = gcd(gcd, diffs[i]);
        }

        int ans = 0;
        for (int i = 0; i < n - 1; i++) {
            ans += (diffs[i] / gcd) - 1;
        }
        System.out.println(ans);
    }

    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
