import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			a.add(new ArrayList<>());
		}

		int[] indegree = new int[N + 1];
		int[] time = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			time[i] = Integer.parseInt(st.nextToken());

			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				int temp = Integer.parseInt(st.nextToken());
				a.get(temp).add(i);

				indegree[i]++;
			}
		}

		bw.write(topologicalSort(N, a, indegree, time) + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	// 위상 정렬
	public static int topologicalSort(int N, ArrayList<ArrayList<Integer>> a, int[] indegree, int[] time) {
		Queue<Integer> q = new LinkedList<>();

		int[] result = new int[N + 1]; // 각각의 작업을 수행하는 데 걸리는 시간
		for (int i = 1; i <= N; i++) {
			result[i] = time[i];
			
			// indegree가 0인 작업을 큐에 넣음.
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int next : a.get(now)) {
				indegree[next]--;

				result[next] = Math.max(result[next], result[now] + time[next]);
				
				// 새롭게 indegree가 0이 된 작업을 큐에 넣음.
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}

		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, result[i]);
		}

		return ans;
	}

}