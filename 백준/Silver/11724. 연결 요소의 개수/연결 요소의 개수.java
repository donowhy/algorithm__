import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static boolean[] check;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        ArrayList<ArrayList<Integer>> fromto = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            fromto.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] temp = br.readLine().split(" ");
            int from = Integer.parseInt(temp[0]);
            int to = Integer.parseInt(temp[1]);
            fromto.get(from).add(to);
            fromto.get(to).add(from);
        }

        check = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if(check[i]) {
                continue;
            }
            result ++;
            dfs(fromto, i);
        }
        System.out.println(result);
    }

    private static void dfs (ArrayList<ArrayList<Integer>> fromto, int now) {

        ArrayList<Integer> array = fromto.get(now);
        if(check[now]) {
            return;
        }
        check[now] = true;
        for (int i = 0; i < array.size(); i++) {
            dfs(fromto, array.get(i));
        }
    }
}
