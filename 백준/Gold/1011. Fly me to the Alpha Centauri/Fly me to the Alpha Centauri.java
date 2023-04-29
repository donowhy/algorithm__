import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int dist = y - x; // 거리 계산
            int n = (int)Math.sqrt(dist); // n : 최대 이동 횟수

            if(n*n == dist) { // 거리가 제곱수일 때
                System.out.println(2*n - 1);
            }
            else if(n*n < dist && dist <= n*n + n) { // 거리가 n^2 < dist <= n^2 + n일 때
                System.out.println(2*n);
            }
            else { // 거리가 n^2 + n < dist일 때
                System.out.println(2*n + 1);
            }
        }
    }
}
