import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long W = Long.parseLong(st.nextToken()); 
        long S = Long.parseLong(st.nextToken()); 

        long cost1 = (X + Y) * W;

        long minXY = Math.min(X, Y);
        long absXY = Math.abs(X - Y);
        long cost2 = (minXY * S) + (absXY * W);

        long cost3;
        
        if (absXY % 2 == 0) {
            cost3 = Math.max(X, Y) * S;
        } else {
            cost3 = (Math.max(X, Y) - 1) * S + W;
        }

        System.out.println(Math.min(cost1, Math.min(cost2, cost3)));
    }
}