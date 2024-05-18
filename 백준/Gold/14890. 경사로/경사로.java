import java.util.*;
import java.io.*;

public class Main{

	static int arr[][];
	static int L;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;

		st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());

		int origin[][]=new int[N][N];
		arr=new int[N*2][N];

		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				origin[i][j]=Integer.parseInt(st.nextToken());
				arr[i][j]=origin[i][j];
			}
		}
		
		int idx=N;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[idx][j] = origin[j][i];
			}
			idx++;
		}
		
		boolean slider[][];
		int count = 0;
		for(int i=0;i<arr.length;i++) {
			slider=new boolean[arr[0].length][2];
			putSlider(slider,i);

			boolean check=true;
			
			for(int j=1;j<arr[0].length;j++) {
				if(arr[i][j]==arr[i][j-1]) continue;
				if(arr[i][j]-arr[i][j-1]==-1&&slider[j][0]) continue;
				if(arr[i][j]-arr[i][j-1]==1&&slider[j-1][1]) continue;

				check=false;
				break;
			}

			if(check) count++;
			
		}
		
		System.out.println(count);


	}

	public static void putSlider(boolean check[][],int idx) {
		for(int i=1;i<arr[0].length-L+1;i++) {
			if(arr[idx][i-1]-arr[idx][i]==1) {
				boolean put = true;
				int stan=arr[idx][i];

				for(int j=0;j<L;j++) 
					if(arr[idx][i+j]!=stan) put = false;

				if(put) {
					for(int j=0;j<L;j++) 
						check[i+j][0] = true;
				}
			}
		}

		for(int i=arr[0].length-1;i>=L;i--) {
			if(arr[idx][i]-arr[idx][i-1]==1) {
				boolean put = true;
				int stan=arr[idx][i-1];

				for(int j=0;j<L;j++) {
					if(arr[idx][i-j-1]!=stan) put = false;
					if(check[i-j-1][0]) put = false;
				}

				if(put) {
					for(int j=0;j<L;j++) 
						check[i-j-1][1] = true;
				}

			}
		}
	}
}