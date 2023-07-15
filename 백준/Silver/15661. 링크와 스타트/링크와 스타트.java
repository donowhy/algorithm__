import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] ability;
    static boolean[] team;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        team = new boolean[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divideTeam(0);
        System.out.println(min);
    }

    static void divideTeam(int idx) {
        if(idx == N) {
            diff();
            return;
        }

        team[idx] = true;
        divideTeam(idx + 1);

        team[idx] = false;
        divideTeam(idx + 1);
    }

    static void diff() {
        int start = 0;
        int link = 0;

        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(team[i] == true && team[j] == true) {
                    start += ability[i][j];
                    start += ability[j][i];
                }
                else if(team[i] == false && team[j] == false) {
                    link += ability[i][j];
                    link += ability[j][i];
                }
            }
        }

        int diff = Math.abs(start - link);

        if(diff == 0) {
            System.out.println(diff);
            System.exit(0);
        }

        min = Math.min(diff, min);
    }
}
