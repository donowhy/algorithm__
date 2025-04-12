import java.io.*;
import java.util.*;

public class Main {
    static Map<Integer, TreeSet<Integer>> xMap = new HashMap<>();
    static Map<Integer, TreeSet<Integer>> yMap = new HashMap<>();
    static int curX, curY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[] dirs = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int tx = x - y;
            int ty = x + y;

            xMap.computeIfAbsent(tx, k -> new TreeSet<>()).add(ty);
            yMap.computeIfAbsent(ty, k -> new TreeSet<>()).add(tx);

            if (i == 0) {
                curX = tx;
                curY = ty;
            }
        }

        for (char dir : dirs) {
            move(dir);
        }

        int finalX = (curX + curY) / 2;
        int finalY = (curY - curX) / 2;
        System.out.println(finalX + " " + finalY);
    }

    static void move(char dir) {
        if (dir == 'A') {
            TreeSet<Integer> set = xMap.get(curX);
            if (set == null) return;
            Integer next = set.higher(curY);
            if (next != null) {
                remove(curX, curY);
                curY = next;
            }
        } else if (dir == 'B') {
            TreeSet<Integer> set = yMap.get(curY);
            if (set == null) return;
            Integer next = set.higher(curX);
            if (next != null) {
                remove(curX, curY);
                curX = next;
            }
        } else if (dir == 'C') {
            TreeSet<Integer> set = yMap.get(curY);
            if (set == null) return;
            Integer next = set.lower(curX);
            if (next != null) {
                remove(curX, curY);
                curX = next;
            }
        } else if (dir == 'D') {
            TreeSet<Integer> set = xMap.get(curX);
            if (set == null) return;
            Integer next = set.lower(curY);
            if (next != null) {
                remove(curX, curY);
                curY = next;
            }
        }
    }

    static void remove(int x, int y) {
        TreeSet<Integer> ySet = xMap.get(x);
        if (ySet != null) {
            ySet.remove(y);
            if (ySet.isEmpty()) xMap.remove(x);
        }
        TreeSet<Integer> xSet = yMap.get(y);
        if (xSet != null) {
            xSet.remove(x);
            if (xSet.isEmpty()) yMap.remove(y);
        }
    }
}
