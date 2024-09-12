import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] collect = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Set<Integer> set = new HashSet<>();
        Long result = 0L;
        int start = 0;

        for (int end = 0; end < n; end++) {
            while (set.contains(collect[end])) {
                set.remove(collect[start]);
                start++;
            }

            set.add(collect[end]);
            result += (end - start + 1); 
        }

        System.out.println(result);
    }
}
