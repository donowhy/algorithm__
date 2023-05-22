import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringTokenizer st;
    static ArrayList<Point> arr = new ArrayList<>();
    static int max;

    static class Point implements Comparable<Point>{
        int x;
        int y;

        public Point(int a, int b){
            this.x = a;
            this.y = b;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return this.y - o.y;
            } else {
                return this.x - o.x;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int xm = Integer.parseInt(st.nextToken());
            int xd = Integer.parseInt(st.nextToken());
            int ym = Integer.parseInt(st.nextToken());
            int yd = Integer.parseInt(st.nextToken());

            int start = xm * 100 + xd;
            int end = ym * 100 + yd;

            arr.add(new Point(start, end));
        }

        Collections.sort(arr);

        int cur_x = 301;
        int cur_y = 301;
        int idx = 0;
        int answer = 0;

        while (cur_y < 1131) {
            int temp = cur_y;

            while (idx < N && arr.get(idx).x <= cur_y){
                if (temp < arr.get(idx).y){
                    temp = Math.max(temp, arr.get(idx).y);
                }
                idx++;
            }
            if (temp == cur_y){
                break;
            }else{
                cur_y = temp;
                answer += 1;
            }

        }
        if (cur_y < 1131){
            System.out.println(0);
        }else{
            System.out.println(answer);
        }
    }
}
