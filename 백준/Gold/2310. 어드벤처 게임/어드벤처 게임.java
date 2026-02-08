import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main { 

    static int n;
    static List<Room> list;
    static boolean[] visited;
    static boolean isPossible; 

    static class Room {
        String type;
        int cost;   
        List<Integer> doors = new ArrayList<>();

        public Room(String type, int cost) {
            this.type = type;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            list = new ArrayList<>();
            list.add(new Room("", 0));

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String type = st.nextToken();
                int cost = Integer.parseInt(st.nextToken());

                Room room = new Room(type, cost);

                while (st.hasMoreTokens()) {
                    int nextRoom = Integer.parseInt(st.nextToken());
                    if (nextRoom == 0) break;
                    room.doors.add(nextRoom);
                }
                list.add(room);
            }

            visited = new boolean[n + 1];
            isPossible = false;

            dfs(1, 0);

            if (isPossible) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
    static void dfs(int currentIdx, int currentMoney) {
       
        if (isPossible) return;

        Room room = list.get(currentIdx);
        int nextMoney = currentMoney;

       if (room.type.equals("L")) {
            if (nextMoney < room.cost) {
                nextMoney = room.cost;
            }
        } else if (room.type.equals("T")) {
            if (nextMoney < room.cost) {
                return;
            }
            nextMoney -= room.cost; 
        }
       
        if (currentIdx == n) {
            isPossible = true;
            return;
        }

        visited[currentIdx] = true;

        for (int nextIdx : room.doors) {
            if (!visited[nextIdx]) {
                dfs(nextIdx, nextMoney);
            }
        }

      visited[currentIdx] = false;
    }
}