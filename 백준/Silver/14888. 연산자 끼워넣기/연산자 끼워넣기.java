import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;
    private static int[] tools = new int[4];
    private static int[] numbers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ea = Integer.parseInt(br.readLine());
        numbers = new int[ea];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < ea; i++) {
            numbers[i] = Integer.parseInt(s[i]);
        }
        String[] t = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            tools[i] = Integer.parseInt(t[i]);
        }

        bruteforce(numbers[0], 1, ea);
        System.out.println(max);
        System.out.println(min);
    }

    private static void bruteforce(int value, int depth, int ea) {
        if (depth == ea) {
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (tools[i] <= 0) continue;
            tools[i]--;
            int calcResult = calc(i, value, depth);
            bruteforce(calcResult, depth + 1, ea);
            tools[i]++;
        }
    }

    private static int calc(int i, int value, int depth) {
        switch (i) {
            case 0: return value + numbers[depth];
            case 1: return value - numbers[depth];
            case 2: return value * numbers[depth];
            case 3: return value / numbers[depth];
            default: return 0;
        }
    }
}
