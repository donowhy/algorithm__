import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static ArrayList<Point> arr = new ArrayList<>();

    static class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.y - o.y; // Sort by y
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());

            if(temp1 > temp2) {
                int tmp = temp1;
                temp1 = temp2;
                temp2 = tmp;
            }
            
            arr.add(new Point(temp1, temp2));
        }

        L = Integer.parseInt(br.readLine());
        Collections.sort(arr);

        PriorityQueue<Integer> queue = new PriorityQueue<>(); // Will store start points
        int maxCount = 0;

        for (Point p : arr) {
            queue.offer(p.x); // Add the start point of the house to the queue
            while (!queue.isEmpty() && queue.peek() < p.y - L) {
                queue.poll(); // Remove houses that are too far to the left
            }
            maxCount = Math.max(maxCount, queue.size());
        }
        System.out.println(maxCount);
    }
}
