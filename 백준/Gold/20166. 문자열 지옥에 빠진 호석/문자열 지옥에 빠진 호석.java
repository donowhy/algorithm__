import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1};  // Directions for row (Up, Down, Left, Right, Diagonal)
    private static final int[] DY = {0, 0, -1, 1, -1, 1, -1, 1};  // Directions for column (Up, Down, Left, Right, Diagonal)

    private static char[][] map;
    private static int N, M, K;
    private static Map<String, Integer> godStrings = new HashMap<>();
    private static String[] inputList;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();  
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        
        map = new char[N + 1][M + 1];
        inputList = new String[K + 1];
        
        for (int i = 1; i <= N; i++) {
            String input = reader.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        // Save god's favorite strings into the map
        for (int i = 1; i <= K; i++) {       
            String input = reader.readLine();
            inputList[i] = input;
            godStrings.put(input, 0);
        }

        // Apply DFS for all points in the map
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                DFS(i, j, 1, String.valueOf(map[i][j])); 
            }
        }

        // Print the result
        for (int i = 1; i <= K; i++) {
            result.append(godStrings.get(inputList[i])).append("\n");
        }  
        
        System.out.println(result.toString());
    }

    private static void DFS(int row, int col, int count, String s) {  
        if (godStrings.containsKey(s)) {
            godStrings.put(s, godStrings.get(s) + 1);
        }  

        if (count < 5) {
            for (int i = 0; i <= 7; i++) {
                int newRow = (row + DX[i] + N) % N + 1;  // Apply circular (ring) logic
                int newCol = (col + DY[i] + M) % M + 1;
                DFS(newRow, newCol, count + 1, s + map[newRow][newCol]); 
            }
        }
    }
}
