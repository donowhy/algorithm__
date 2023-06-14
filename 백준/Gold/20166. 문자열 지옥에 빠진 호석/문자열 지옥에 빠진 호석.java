import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int N, M, K;
    static char[][] map;
    static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
    static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
    static TrieNode root = new TrieNode();

    static class TrieNode {
        HashMap<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    static void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.count++;
    }

    static int search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.get(c);
            if (node == null) return 0;
        }
        return node.count;
    }

    static void dfs(int x, int y, String word) {
        if (word.length() == 5) {
            insert(word);
            return;
        }

        for (int dir = 0; dir < 8; dir++) {
            int nx = (x + dx[dir] + N) % N;
            int ny = (y + dy[dir] + M) % M;
            dfs(nx, ny, word + map[nx][ny]);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmk = br.readLine().split(" ");
        N = Integer.parseInt(nmk[0]);
        M = Integer.parseInt(nmk[1]);
        K = Integer.parseInt(nmk[2]);
        map = new char[N][M];
        
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                dfs(i, j, String.valueOf(map[i][j]));

        for (int k = 0; k < K; k++) {
            String word = br.readLine();
            System.out.println(search(word));
        }
    }
}
