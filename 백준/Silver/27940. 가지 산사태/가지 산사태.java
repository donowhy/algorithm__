import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int m = Integer.parseInt(s[1]);
        int k = Integer.parseInt(s[2]);

        int count = 0;
        for (int i = 0; i < m; i++) {
            String[] info = br.readLine().split(" ");
            int rain = Integer.parseInt(info[1]);
            count += rain;
            if(count > k) {
                System.out.println(i+1 + " " + 1);
                return;
            }
        }
        System.out.println(-1);
    }
}
