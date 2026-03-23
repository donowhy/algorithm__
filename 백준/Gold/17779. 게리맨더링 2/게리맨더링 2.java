import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] map;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // x, y, d1, d2 완전탐색
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                for (int d1 = 1; d1 <= n; d1++) {
                    for (int d2 = 1; d2 <= n; d2++) {
                        if (isValid(x, y, d1, d2)) {
                            answer = Math.min(answer, counting(x, y, d1, d2));
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean isValid(int x, int y, int d1, int d2) {
        return (1 <= x && x + d1 + d2 <= n
                && 1 <= y - d1 && y + d2 <= n);
    }

    private static int counting(int x, int y, int d1, int d2) {
        boolean[][] isBoundary = new boolean[n + 1][n + 1];
        for (int i = 0; i <= d1; i++) {
            isBoundary[x + i][y - i] = true; 
            isBoundary[x + d2 + i][y + d2 - i] = true; 
        }
        for (int i = 0; i <= d2; i++) {
            isBoundary[x + i][y + i] = true; 
            isBoundary[x + d1 + i][y - d1 + i] = true;
        }

        int[] people = new int[6];
        int totalPeople = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                totalPeople += map[i][j];
            }
        }

        // 1번 구역
        for (int r = 1; r < x + d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (isBoundary[r][c]) break; 
                people[1] += map[r][c];
            }
        }
        // 2번 구역
        for (int r = 1; r <= x + d2; r++) {
            for (int c = n; c > y; c--) { 
                if (isBoundary[r][c]) break;
                people[2] += map[r][c];
            }
        }
        // 3번 구역
        for (int r = x + d1; r <= n; r++) {
            for (int c = 1; c < y - d1 + d2; c++) {
                if (isBoundary[r][c]) break;
                people[3] += map[r][c];
            }
        }
        // 4번 구역
        for (int r = x + d2 + 1; r <= n; r++) {
            for (int c = n; c >= y - d1 + d2; c--) {
                if (isBoundary[r][c]) break;
                people[4] += map[r][c];
            }
        }

        people[5] = totalPeople - (people[1] + people[2] + people[3] + people[4]);

        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 1; i <= 5; i++) {
            max = Math.max(max, people[i]);
            min = Math.min(min, people[i]);
        }
        return max - min;
    }
}