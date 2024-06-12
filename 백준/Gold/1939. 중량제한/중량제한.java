import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static class LoadLimit {
        int to, limit;

        public LoadLimit(int to, int limit) {
            this.to = to;
            this.limit = limit;
        }
    }

    private static ArrayList<ArrayList<LoadLimit>> loadLimits = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        for (int i = 0; i <= n; i++) {
            loadLimits.add(new ArrayList<>());
        }

        int minLimit = Integer.MAX_VALUE;
        int maxLimit = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            String[] values = br.readLine().split(" ");
            int from = Integer.parseInt(values[0]);
            int to = Integer.parseInt(values[1]);
            int limit = Integer.parseInt(values[2]);
            loadLimits.get(from).add(new LoadLimit(to, limit));
            loadLimits.get(to).add(new LoadLimit(from, limit));
            minLimit = Math.min(minLimit, limit);
            maxLimit = Math.max(maxLimit, limit);
        }

        String[] factory = br.readLine().split(" ");
        int AFactory = Integer.parseInt(factory[0]);
        int BFactory = Integer.parseInt(factory[1]);

        System.out.println(binarySearch(AFactory, BFactory, minLimit, maxLimit));
    }

    private static boolean bfs(int start, int end, int limit) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[loadLimits.size()];
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                return true;
            }
            for (LoadLimit next : loadLimits.get(current)) {
                if (!visited[next.to] && next.limit >= limit) {
                    visited[next.to] = true;
                    queue.add(next.to);
                }
            }
        }
        return false;
    }

    private static int binarySearch(int start, int end, int minLimit, int maxLimit) {
        int left = minLimit;
        int right = maxLimit;
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(start, end, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}
