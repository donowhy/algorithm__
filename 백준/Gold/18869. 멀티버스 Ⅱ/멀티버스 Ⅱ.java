import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");

        // 우주 갯수
        int n = Integer.parseInt(s[0]);
        // 행성 갯수
        int m = Integer.parseInt(s[1]);

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] ss = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(ss[j]);
            }
        }

        // 순위 배열을 사용하여 중복을 체크하는 해시맵
        HashMap<String, Integer> rankMap = new HashMap<>();
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int[] ranks = getRanks(map[i]);
            String key = Arrays.toString(ranks);

            // 이미 동일한 순위 배열이 존재한다면 카운트를 증가시킴
            if (rankMap.containsKey(key)) {
                cnt += rankMap.get(key);
                rankMap.put(key, rankMap.get(key) + 1);
            } else {
                rankMap.put(key, 1);
            }
        }

        System.out.println(cnt);
    }

    // 주어진 배열에 대한 순위 배열 생성
    private static int[] getRanks(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        int[] ranks = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ranks[i] = Arrays.binarySearch(sorted, arr[i]);
        }

        return ranks;
    }
}
