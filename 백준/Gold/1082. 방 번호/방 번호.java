import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ps = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int minCost = 51;
        int minIdx = -1;
        
        for (int i = 0; i < n; i++) {
            ps[i] = Integer.parseInt(st.nextToken());
            if (minCost >= ps[i]) { 
                minCost = ps[i];
                minIdx = i;
            }
        }

        int m = Integer.parseInt(br.readLine());
        
        int[] result = new int[51]; 
        int count = 0;

        int firstMinIdx = -1;
        int firstMinCost = 51;
        for (int i = 1; i < n; i++) {
            if (firstMinCost > ps[i]) {
                firstMinCost = ps[i];
                firstMinIdx = i;
            }
        }

        if (firstMinIdx == -1 || firstMinCost > m) { 
            System.out.println(0);
            return;
        }

        result[count++] = firstMinIdx;
        m -= firstMinCost;

        while (m >= minCost) {
            result[count++] = minIdx;
            m -= minCost;
        }

        for (int i = 0; i < count; i++) {
            for (int j = n - 1; j >= 0; j--) {
                int extraCost = ps[j] - ps[result[i]];
                if (m >= extraCost) {
                    result[i] = j;
                    m -= extraCost;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) sb.append(result[i]);
        System.out.println(sb.toString());
    }
}