import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static int n, k;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);

        arr = new int[n]; // 배열 초기화
        s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        int st = 0;
        int en = 0;
        int kcnt = 0; // 홀수의 개수를 추적하는 변수
        int result = 0; // 최종 결과 값

        // 투포인터 방식으로 풀기
        while (en < n) { // en이 n보다 작을 때까지만 실행
            
            if (arr[en] % 2 == 0) {
                en++;
            } else {
                if (kcnt < k) { // 아직 k보다 작으면 홀수 사용 가능
                    kcnt++;
                    en++;
                } else { // k 이상이면 st 포인터를 움직여 홀수를 제외
                    if (arr[st] % 2 != 0) {
                        kcnt--;
                    }
                    st++;
                }
            }

            result = Math.max(result, en - st - kcnt); // 현재 길이를 비교
        }

        System.out.println(result);
    }
}
