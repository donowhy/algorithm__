import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        List<Long> trees = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<n; i++) {
            long tree = Long.parseLong(st.nextToken());
            trees.add(tree);
        }

        trees.sort(Long::compareTo);

        long result = twoPoint(m, trees);

        System.out.println(result);

    }

    private static long twoPoint(long target, List<Long> trees) {
        long start = 0;
        long end = trees.get(trees.size() - 1);

        long result = 0;

        while(start < end) {
            long mid = (start + end) / 2;
            long temp = 0L;

            for (Long tree : trees) {
                if (tree > mid) {
                    temp += tree - mid;
                }
            }

            if(temp >= target) {
                start = mid + 1;
                result = Math.max(result, mid);
            } else {
                end = mid;
            }
        }

        return result;
    }
}
