import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");

            a[i] = Integer.parseInt(s[0]);
            b[i] = Integer.parseInt(s[1]);
            c[i] = Integer.parseInt(s[2]);
            d[i] = Integer.parseInt(s[3]);
        }

        int[] newA = new int[n * n];
        int[] newB = new int[n * n];
        int idx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newA[idx] = a[i] + b[j];
                newB[idx] = c[i] + d[j];
                idx++;
            }
        }

        Arrays.sort(newA);
        Arrays.sort(newB);

        long result = 0L;

        for (int i = 0; i < newA.length; i++) {
            int target = -newA[i];  // newB에서 찾고자 하는 값은 -newA[i]입니다.

            int lower = lowerBound(newB, target);
            int upper = upperBound(newB, target);

            result += (upper - lower);  // 범위 안에 있는 값의 개수를 더합니다.
        }

        System.out.println(result);
    }

    // lower bound 찾기
    private static int lowerBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    // upper bound 찾기
    private static int upperBound(int[] arr, int target) {
        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
