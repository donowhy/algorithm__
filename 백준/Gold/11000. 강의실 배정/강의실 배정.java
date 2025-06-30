import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[][] meeting = new int[n][2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meeting[i][0] = Integer.parseInt(st.nextToken());
            meeting[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(meeting, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        rooms.add(meeting[0][1]);

        for (int i = 1; i < n; i++) {
            if (meeting[i][0] >= rooms.peek()) {
                rooms.poll();
            }
            rooms.add(meeting[i][1]);
        }

        System.out.println(rooms.size());
    }
}
