import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Fire {
        int r, c, m, s, d;
        public Fire(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    private static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int n, m, k;
    private static List<Fire> fireballs = new ArrayList<>();
    private static List<Fire>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fire(r, c, mass, s, d));
        }

        while (k-- > 0) {
            move();
            check();
        }

        int totalMass = 0;
        for (Fire f : fireballs) {
            totalMass += f.m;
        }
        System.out.println(totalMass);
    }

    private static void move() {
        for (Fire fire : fireballs) {
            fire.r = (fire.r + n + dr[fire.d] * (fire.s % n)) % n;
            fire.c = (fire.c + n + dc[fire.d] * (fire.s % n)) % n;

            map[fire.r][fire.c].add(fire);
        }
    }

    private static void check() {
        List<Fire> nextFireballs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = map[i][j].size();

                if (size == 1) {
                    nextFireballs.add(map[i][j].get(0));
                } else if (size >= 2) {
                    int sumM = 0, sumS = 0;
                    boolean allEven = true, allOdd = true;

                    for (Fire f : map[i][j]) {
                        sumM += f.m;
                        sumS += f.s;
                        if (f.d % 2 == 0) allOdd = false;  
                        else allEven = false;
                    }

                    int newM = sumM / 5;
                    if (newM > 0) {
                        int newS = sumS / size;
                        int[] newDirs = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                        for (int dir : newDirs) {
                            nextFireballs.add(new Fire(i, j, newM, newS, dir));
                        }
                    }
                }

                map[i][j].clear();
            }
        }
        fireballs = nextFireballs;
    }
}