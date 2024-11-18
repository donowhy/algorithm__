import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);
        boolean[] arr = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num % 2 == 0;
        }

        // 투포인터
        int maxLen = 0;
        int l = 0;
        int r = 0;
        int ct = 0;
        while (r < n) {
            if (ct < k) {
                if (!arr[r]) {
                    ct++;
                }
                r++;
                maxLen = Math.max(r - l - ct, maxLen);
            } else if (arr[r]) {
                r++;
                maxLen = Math.max(r - l - ct, maxLen);
            } else {
                if (!arr[l]) {
                    ct--;
                }
                l++;
            }
        }

        System.out.println(maxLen);
    }
}