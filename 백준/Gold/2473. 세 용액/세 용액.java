import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); // 배열 정렬

        long ans = Long.MAX_VALUE; // 세 용액의 합의 최소값
        int a = 0, b = 0, c = 0; // 최소값일 때의 용액

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1; // 투 포인터
            while (left < right) {
                long sum = (long) arr[i] + arr[left] + arr[right]; // 세 용액의 합
                if (Math.abs(sum) < ans) { // 최소값 갱신
                    ans = Math.abs(sum);
                    a = arr[i];
                    b = arr[left];
                    c = arr[right];
                }
                if (sum < 0) { // 합이 음수일 경우, left 용액 값을 늘림
                    left++;
                } else { // 합이 양수일 경우, right 용액 값을 줄임
                    right--;
                }
            }
        }

        System.out.println(a + " " + b + " " + c);
    }
}
