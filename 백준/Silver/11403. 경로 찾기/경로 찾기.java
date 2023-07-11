import java.io.*;
import java.util.*;

public class Main {

	static int[][] matrix;
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); 
		matrix=new int[n][n];	//인접행렬 
		int tmp;
		
		for(int i=0; i<n; i++) {	//입력 
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				matrix[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {	//첫번째 행부터 갈 수 있는 길들을 큐에 추가 
				if(matrix[i][j]==1) {	//갈수 있는 길 
					queue.add(j);		//큐에 추가 
				}
			}
			while(!queue.isEmpty()) {	//큐가 비지 않았으면 
				tmp=queue.poll();		//큐에 있는 데이터를 꺼낸 뒤 잠시 저장 
				BFS(i,tmp);		//BFS메소드 호출 
			}
		}
			
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				bw.write(matrix[i][j]+" ");		
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	
	}
	
	public static void BFS(int i,int tmp) {		//시작한 지점 i, 큐에서 나온 변수 tmp 
			matrix[i][tmp]=1;	// i에서 tmp는 갈 수 있으므로 1로 변경 
			for(int j=0; j<matrix[0].length; j++) {		//matrix[0].length==n 
				if(matrix[tmp][j]==1&&matrix[i][j]!=1) {		//matrix[i][j]==1인데 큐에 넣으면 무한루프를 돌게 될 수 있으므로 조건 추가 
					queue.add(j);	//갈 수 있는 길이므로 큐에 추가 
				}
			}	
	}

}