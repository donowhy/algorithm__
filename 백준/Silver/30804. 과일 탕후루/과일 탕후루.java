import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        int n;
        String[] s;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(br.readLine());
            s = br.readLine().split(" ");
        }

        int[] tang = new int[n];
        for (int i = 0; i < n; i++) {
            tang[i] = Integer.parseInt(s[i]);
        }

        System.out.println(findFruitsMaximum(tang, n));
    }

    public static int findFruitsMaximum(int[] tang, int n) {
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int maxLen = 0, start = 0;

        for (int end = 0; end < n; end++) {
            fruitCount.put(tang[end], fruitCount.getOrDefault(tang[end], 0) + 1);

            while (fruitCount.size() > 2) {
                fruitCount.put(tang[start], fruitCount.get(tang[start]) - 1);
                if (fruitCount.get(tang[start]) == 0) {
                    fruitCount.remove(tang[start]);
                }
                start++;
            }

            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }
}
