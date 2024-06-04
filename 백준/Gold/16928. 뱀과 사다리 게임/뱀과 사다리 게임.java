import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int sadari = Integer.parseInt(s[0]);
        int bam = Integer.parseInt(s[1]);

        int[] map = new int[101];
        
        for (int i = 1; i <= 100; i++) {
            map[i] = i;
        }

        for (int i = 0; i < sadari + bam; i++) {
            String[] temp = br.readLine().split(" ");
            map[Integer.parseInt(temp[0])] = Integer.parseInt(temp[1]);
        }

        System.out.println(bfs(map));
    }

    private static int bfs (int[] map) {
        Queue<Location> q = new LinkedList<>();
        boolean[] visited = new boolean[101];

        q.add(new Location(1, 0));
        visited[1] = true;

        while (!q.isEmpty()) {
            Location current = q.poll();

            if(current.nm == 100) {
                return current.count;
            }

            for (int i = 1; i <= 6; i++) {
                int nextNm = current.nm + i;
                if(nextNm > 100) continue; 
                if(visited[nextNm]) continue;

                visited[nextNm] = true;
                q.add(new Location(map[nextNm], current.count + 1));
            }
        }

        return -1;
    }

    private static class Location {
        int nm, count;

        public Location(int nm, int count) {
            this.nm = nm;
            this.count = count;
        }
    }
}
