import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] values = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        long count = 0;

        for (int i = 0; i < n - 2; i++) {
            int target = values[i];
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = target + values[left] + values[right];

                if (sum == 0) {
                    if (values[left] == values[right]) {
                        int len = right - left + 1;
                        count += (long) len * (len - 1) / 2;
                        break;
                    }

                    int leftVal = values[left];
                    int rightVal = values[right];
                    int leftCount = 0;
                    int rightCount = 0;

                    while (left < right && values[left] == leftVal) {
                        left++;
                        leftCount++;
                    }

                    while (right >= left && values[right] == rightVal) {
                        right--;
                        rightCount++;
                    }

                    count += (long) leftCount * rightCount;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
