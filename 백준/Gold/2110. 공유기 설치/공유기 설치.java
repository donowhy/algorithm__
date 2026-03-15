import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n];

        for(int i = 0; i < n; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(map);

        int res = find();
        System.out.println(res);
    }

    private static int find() {
        int s = 1;
        int e = map[n-1] - map[0];
        int res = 0;
        
        while(s <= e) {
            int mid = (s + e) / 2;
            int cnt = 1;
            int cur = map[0];

            for(int i = 1; i < n; i++) {
                if(map[i] - cur >= mid){ 
                    cnt++;
                    cur = map[i];
                }
            }

            for(int i = 1; i < n; i++) {
                if(map[i] - cur >= mid){ 
                    cnt++;
                    cur = map[i];
                }
            }

            if(cnt >= m) {
                res = mid;
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        
        return res;
    }
}