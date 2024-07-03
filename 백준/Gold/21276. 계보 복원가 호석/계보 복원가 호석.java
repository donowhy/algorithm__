import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M;
	static String[] human;
	static HashMap<String,Integer> map;
	static ArrayList<ArrayList<Integer>> list;
	static int indegree[];
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine()); // 석호촌에 살고 있는 사람의 수
		human=new String[N]; 
		indegree=new int[N];
		
		// 사람들의 이름을 입력받아 정렬
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)
		{
			human[i]=st.nextToken();
		}
		Arrays.sort(human);
		
		// 사람들의 이름에 인덱스 부여
		map=new HashMap<>();
		for(int i=0;i<N;i++)
		{
			map.put(human[i], i);
		}
		
		// 계보 정보를 저장할 list 생성
		list=new ArrayList<>();
		for(int i=0;i<N;i++)
		{
			list.add(new ArrayList<Integer>());
		}
		
		// 가문의 정보 입력받기
		M=Integer.parseInt(br.readLine());
		for(int i=0;i<M;i++)
		{
			st= new StringTokenizer(br.readLine());
			int idx1=map.get(st.nextToken()); // 자손 인덱스
			int idx2=map.get(st.nextToken()); // 조상 인덱스
			
			list.get(idx2).add(idx1); // 조상이 자손을 가르킴
			indegree[idx1]++; // 자손의 차수 세기
		}
		
		
		// 위상정렬
		solve();
	}
	static void solve()
	{
		Queue<Integer> q=new LinkedList<>(); // 위상정렬을 위한 큐
		ArrayList<Integer> root=new ArrayList<>(); // 시조들의 이름을 저장할 리스트
		for(int i=0;i<N;i++)
		{
			if(indegree[i]==0) // 시조이면 큐와 리스트에 인덱스 저장
			{
				root.add(i);
				q.offer(i);
			}
		}
		
		ArrayList<ArrayList<Integer>> children=new ArrayList<>();
		for(int i=0;i<N;i++)
		{
			children.add(new ArrayList<Integer>());
		}
		// 위상정렬
		while(!q.isEmpty())
		{
			//cur인덱스의 자식 조사
			int cur=q.poll();
			
			// cur 인덱스의 자식 조사
			for(int next:list.get(cur))
			{
				// 자식의 차수 낮추기
				indegree[next]--;
				if(indegree[next]==0) // next인덱스의 부모가 cur인덱스이면
				{
					children.get(cur).add(next); //cur인덱스의 자식으로 next추가
					q.offer(next);
				}
			}
		}
		
		System.out.println(root.size());
		for(int idx : root) {
			System.out.print(human[idx]+" ");
		}
		System.out.println();
		for(int i=0;i<N;i++)
		{
			System.out.print(human[i]+" "+children.get(i).size()+" ");
			children.get(i).sort(null); // i인덱스의 자식을 오름차순으로 정렬
			for(int idx: children.get(i))
			{
				System.out.print(human[idx]+" ");
			}
			System.out.println();
		}
	}
}