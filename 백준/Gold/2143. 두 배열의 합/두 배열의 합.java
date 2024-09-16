import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(s[i]);
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        s = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(s[i]);
        }

        ArrayList<Integer> aa = new ArrayList<>();
        ArrayList<Integer> bb = new ArrayList<>();

        // 부분합 계산 (a 배열)
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                aa.add(sum);
            }
        }

        // 부분합 계산 (b 배열)
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                bb.add(sum);
            }
        }

        // 부분합 정렬
        Collections.sort(aa);
        Collections.sort(bb);

        // t를 만드는 쌍의 개수를 저장할 변수
        long count = 0;

        // a의 각 부분합에 대해, b에서 t - a[i]가 나오는 경우의 수를 찾음
        for (int sumA : aa) {
            int target = t - sumA;

            // lower bound와 upper bound의 차이로 개수를 구함
            int lower = lowerBinary(target, bb);
            int upper = upperBinary(target, bb);
            count += (upper - lower);
        }

        System.out.println(count);
    }

    // lower bound: target 이상이 처음 나오는 위치
    private static int lowerBinary(int target, ArrayList<Integer> bb) {
        int l = 0;
        int h = bb.size();

        while (l < h) {
            int mid = (l + h) / 2;
            if (bb.get(mid) >= target) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // upper bound: target을 초과하는 값이 처음 나오는 위치
    private static int upperBinary(int target, ArrayList<Integer> bb) {
        int l = 0;
        int h = bb.size();

        while (l < h) {
            int mid = (l + h) / 2;
            if (bb.get(mid) > target) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
