import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine());
            int count = 0;

            for(int j=0; j<n; j++) {
                st = new StringTokenizer(br.readLine());

                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                if(isIn(x1, y1, cx, cy, r) ^ isIn(x2, y2, cx, cy, r)) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    static boolean isIn(int x, int y, int cx, int cy, int r) {
        return (x-cx)*(x-cx) + (y-cy)*(y-cy) < r*r;
    }
}
