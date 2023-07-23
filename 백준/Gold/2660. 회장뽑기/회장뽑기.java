import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] graph;
    static final int INF = 1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];
        
        // 그래프 초기화
        for(int i = 1; i <= N; i++){
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == -1 && b == -1) break;
            
            graph[a][b] = 1;
            graph[b][a] = 1;
        }

        // 플로이드-와샬 알고리즘
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(graph[i][k] + graph[k][j] < graph[i][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int[] score = new int[N+1];
        int min = INF;
        
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                score[i] = Math.max(score[i], graph[i][j]);
            }
            min = Math.min(min, score[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        int count = 0;
        
        for(int i = 1; i <= N; i++){
            if(score[i] == min){
                count++;
                sb.append(i).append(' ');
            }
        }
        
        System.out.println(min + " " + count);
        System.out.println(sb);
    }
}
