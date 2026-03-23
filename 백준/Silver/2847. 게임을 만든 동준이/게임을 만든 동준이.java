import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        int result = 0;

        for (int i = n - 2; i >= 0; i--) {
            if (map[i] >= map[i + 1]) {
                int target = map[i + 1] - 1;
                result += (map[i] - target); 
                map[i] = target;
            }
        }

        System.out.println(result);
    }
}