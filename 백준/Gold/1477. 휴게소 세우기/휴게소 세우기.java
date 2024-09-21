import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, m, l;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        l = Integer.parseInt(s[2]);

        int[] restStops = new int[n + 2];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            restStops[i] = Integer.parseInt(input[i]);
        }
        restStops[n] = 0; // 고속도로 시작점
        restStops[n + 1] = l; // 고속도로 끝점

        Arrays.sort(restStops);

        // 이분 탐색으로 구간의 최대 길이의 최소값을 찾는다
        int left = 1;
        int right = l;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canPlaceMoreStops(restStops, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canPlaceMoreStops(int[] restStops, int maxDistance) {
        int count = 0;
        for (int i = 1; i < restStops.length; i++) {
            int distance = restStops[i] - restStops[i - 1];
            // 추가해야 하는 휴게소의 수는 (거리 - 1) / 최대 거리
            count += (distance - 1) / maxDistance;
        }
        return count <= m;
    }
}
