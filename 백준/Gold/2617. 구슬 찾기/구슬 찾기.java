import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		// 구슬찾기 플로이드 와샬
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n+1][n+1];
        //반의 값을 구함
		int half = (n/2)+1;
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//크기 a>b
			arr[a][b] = 1; // a가 b보다 큼   a<-b
			arr[b][a] = -1; // b가 a보다 작음 b->a
		}
		for(int i=1;i<=n;i++) {//거쳐가는 구슬
			for(int j= 1;j<=n;j++) {//출발 구슬
				for(int k=1;k<=n;k++) {//도착 구슬
                	//구슬의 연결관계가 있을때
                    //출발 구슬-> 거쳐가는 구슬 , 거쳐가는 구슬-> 출발 구슬
                    //이런식으로 위치가 정렬되어 출발구슬이 도착구슬과 직접적인 연결이 아니어도
                    //위치가 지정되는 조건이 하나 더 성립됨
					if(arr[k][i] != 0 && arr[j][i] == arr[i][k])
						arr[j][k] = arr[j][i];
				}
			}
		}
		 int[] big = new int[n+1];
		 int[] small = new int[n+1];
		//뒤에 오는 조건, 앞에 오는 조건의 개수를 각각 세어 저장
		    for (int i = 1; i <= n; i++ ) {
		        for (int j = 1; j <= n; j++) {

		            if (arr[i][j] == 1) 
		                big[i]++;
		            
		            if (arr[i][j] == -1) 
		                small[i]++;
		            
		        }
		    }
		    int ans=0;
            //조건이 총구슬의 반이 넘으면 답의 개수를 늘려준다
		    for(int i=1;i<=n;i++) {
		    	if(big[i] >= half) ans++;
		    	if(small[i] >= half ) ans++;
		    }
		    System.out.println(ans);

	}

}