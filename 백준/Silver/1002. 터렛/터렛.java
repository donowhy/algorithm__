import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        for(int i=0; i<N ;i++){
            StringTokenizer sting = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(sting.nextToken());
            int y1 = Integer.parseInt(sting.nextToken());
            int r1 = Integer.parseInt(sting.nextToken());
            int x2 = Integer.parseInt(sting.nextToken());
            int y2 = Integer.parseInt(sting.nextToken());
            int r2 = Integer.parseInt(sting.nextToken());

            int answer = find(x1, y1, r1, x2, y2, r2);
            System.out.println(answer);
        }
    }

    static int find(int x1, int y1, int r1, int x2, int y2, int r2) {
        // 두 원의 중심 좌표 간의 거리 계산
        double d = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));

        // 두 원이 일치하는 경우
        if(x1 == x2 && y1 == y2 && r1 == r2) return -1;

        // 두 원이 외접하거나 내접하는 경우
        if(d == r1+r2 || d == Math.abs(r1-r2)) return 1;

        // 두 원이 만나지 않는 경우
        if(d > r1+r2 || d < Math.abs(r1-r2)) return 0;

        // 두 원이 두 점에서 만나는 경우
        return 2;
    }
}
