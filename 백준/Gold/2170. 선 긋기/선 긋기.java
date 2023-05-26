import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Point> arr = new ArrayList<Point>();

    static class Point {
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new Point(x, y));
        }

        Collections.sort(arr, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                if (p1.x == p2.x) {
                    return Integer.compare(p1.y, p2.y);
                } else {
                    return Integer.compare(p1.x, p2.x);
                }
            }
        });

        int i = 0;
        int dis = 0;
        while (i < N) {
            int start = arr.get(i).x;
            int end = arr.get(i).y;

            while (++i < N) {
                if (arr.get(i).x <= end) {
                    end = Math.max(end,arr.get(i).y);
                } else {
                    break;
                }
            }
            dis += end - start;
        }
        System.out.println(dis);
    }
}
