import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        Long a = Long.parseLong(s[0]);
        Long b = Long.parseLong(s[1]);

        Long start = Math.min(a, b);
        Long end = Math.max(a, b);

        System.out.println(Math.max(end - start - 1, 0));

        for (long i = start + 1; i < end; i++) {
            System.out.printf(i + " ");
        }
    }
}
