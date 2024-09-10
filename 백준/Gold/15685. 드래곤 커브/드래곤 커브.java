import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static private int n;
	//0<=x,y<=100 이라서 크기를 101로 해줘야됨.
	static private final boolean[][] map = new boolean[101][101];
	static private List<Integer> dirList;
	//0:오른쪽 , 1:위쪽, 2:왼쪽, 3:아래쪽
	//하지만 문제에서 x,y의 좌표가 반대라서 아래 dx,dy도 반대로 넣어줘야 답이나옴.
	static private int[] dx={1,0,-1,0};
	static private int[] dy={0,-1,0,1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dirList = new LinkedList<Integer>();
			addDirAll(d,g);//각 세대마다 방향을 전부 넣기.
			drawDragon(x,y);//시작점부터 모든뱡향으로 좌표 하나씩 방문처리.
		}
		
		int ans = checkSquare();
		System.out.println(ans);
		
	}
	
	//이전세대*2 = 다음세대 방향 수
	//즉, 3세대는 총 8번의 방향정보가 나옴.
	public static void addDirAll(int d, int g) {
		dirList.add(d);//0세대일때 방향 추가
		
		for(int i=1; i<=g; i++) {
			for(int j=dirList.size()-1; j>=0; j--) {
				dirList.add((dirList.get(j)+1)%4);
			}
		}
		
	}
	
	//꼭지점 그리기.
	//주의: 문제에서 x,y좌표가 거꾸로 돼있음.
	public static void drawDragon(int x, int y) {
		map[x][y] = true;//초기위치 그리기.
		
		int nx=x , ny=y;
		for(int i=0; i<dirList.size(); i++) {
			int d = dirList.get(i);
			
			nx += dx[d];
			ny += dy[d];
			
			map[nx][ny] = true;
		}
	}
	
	//꼭지점이 4개 채워진 사각형 개수 카운트
	public static int checkSquare() {
		int cnt=0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i][j+1] && map[i+1][j] && map[i+1][j+1]) cnt++;
			}
		}
		
		return cnt;
	}
}