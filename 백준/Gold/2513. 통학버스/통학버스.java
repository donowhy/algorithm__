import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n, k, s;
    private static PriorityQueue<Node> leftQ = new PriorityQueue<>();
    private static PriorityQueue<Node> rightQ = new PriorityQueue<>();
    private static long totalDistance = 0L;

    private static class Node implements Comparable<Node> {
        int dist, students; 

        public Node(int dist, int students) {
            this.dist = dist;
            this.students = students;
        }

        @Override
        public int compareTo(Node o) {
            return o.dist - this.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken());
            int students = Integer.parseInt(st.nextToken());

            if (loc < s) {
                leftQ.add(new Node(s - loc, students));
            } else if (loc > s) {
                rightQ.add(new Node(loc - s, students));
            }
        }

        calculateDistance(leftQ);
        calculateDistance(rightQ);

        System.out.println(totalDistance);
    }

    private static void calculateDistance(PriorityQueue<Node> pq) {
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            int trips = cur.students / k;
            if (cur.students % k > 0) trips++; 

            totalDistance += (long) trips * 2 * cur.dist;

            int emptySeats = trips * k - cur.students;

            while (emptySeats > 0 && !pq.isEmpty()) {
                Node next = pq.poll();

                if (next.students <= emptySeats) {
                    emptySeats -= next.students;
                } else {
                    next.students -= emptySeats;
                    emptySeats = 0;
                    pq.add(next);
                }
            }
        }
    }
}