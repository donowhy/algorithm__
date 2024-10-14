import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] sour;
    static int[] bitter;
    static int minDifference = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sour = new int[n];
        bitter = new int[n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        findMinDifference(0, 1, 0, 0);
       
        System.out.println(minDifference);
    }

    static void findMinDifference(int idx, int totalSour, int totalBitter, int count) {
        if (idx == n) {
            if (count > 0) {
                int diff = Math.abs(totalSour - totalBitter);
                minDifference = Math.min(minDifference, diff);
            }
            return;
        }

        findMinDifference(idx + 1, totalSour * sour[idx], totalBitter + bitter[idx], count + 1);
        findMinDifference(idx + 1, totalSour, totalBitter, count);
    }
}
