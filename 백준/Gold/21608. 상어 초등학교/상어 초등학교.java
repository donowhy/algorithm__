import java.util.*;
import java.io.*;

public class Main {
	// N, 만족도 총 합
	static int N, sum;
	// 학생 배열, 상 우 하 좌
	static int[] students, dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };
	// 학생들이 앉은 map
	static int[][] map;
	// 학생별 좋아하는 학생들
	static Map<Integer, Set<Integer>> preferences;

	public static void main(String[] args) throws Exception {
		// 입력
		init();

		// 풀이
		solution();

		// 결과 출력
		printResult();
	}

	static void solution() {
		// 1. 학생들 자리 배치
		for (int i = 0; i < students.length; i++) {
			Seat seat = findSeat(students[i]);
			map[seat.x][seat.y] = students[i];
		}

		// 2. 점수 합산
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 주변 학생 수에 따라 점수 합산
				int count = getStudentSum(i, j, map[i][j]);
				if (count > 0) {
					sum += (int) Math.pow(10, count - 1);
				}
			}
		}
	}

	// 앉을 좌석 찾기
	static Seat findSeat(int student) {
		Seat seat = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이미 자리에 누구 앉아있으면 skip
				if (map[i][j] > 0) {
					continue;
				}
				// 현재 자리의 정보 (x y 좌표, 인접한 좋아하는 학생 수, 빈칸 수)
				Seat cur = new Seat(i, j, getStudentSum(i, j, student), getEmptySum(i, j));
				// 비교할 seat이 null이라면 초기화 후 skip
				if (seat == null) {
					seat = cur;
					continue;
				}

				// 이전 좌석과 현재 좌석 비교
				if (seat.compareTo(cur) > 0) {
					seat = cur;
				}
			}
		}
		return seat;
	}

	// 인접한 좋아하는 학생 수
	static int getStudentSum(int x, int y, int student) {
		int count = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위 벗어나면 skip
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			// 인접한 학생이 좋아하는 학생에 포함되면 count 증가
			if (preferences.get(student).contains(map[nx][ny])) {
				count++;
			}
		}

		return count;
	}

	// 빈칸 수
	static int getEmptySum(int x, int y) {
		int count = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			// 범위 벗어나면 skip
			if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
				continue;
			}
			// 빈칸이면 count 증가
			if (map[nx][ny] == 0) {
				count++;
			}
		}

		return count;
	}

	// 결과 출력
	static void printResult() {
		System.out.println(sum);
	}

	// 입력
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		sum = 0;
		map = new int[N][N];
		students = new int[N * N];
		preferences = new HashMap<>();
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			// 학생 배열에 기록
			students[i] = student;
			// 학생별 좋아하는 학생들 기록
			preferences.put(student, new HashSet<>());
			for (int j = 0; j < 4; j++) {
				preferences.get(student).add(Integer.parseInt(st.nextToken()));
			}
		}
	}

	// 좌석 정보 저장할 Seat 클래스
	static class Seat implements Comparable<Seat> {
		// x y 좌표, 주변 좋아하는 학생 수, 주변 빈칸 수
		int x, y, studentSum, emptySum;

		public Seat(int x, int y, int studentSum, int emptySum) {
			this.x = x;
			this.y = y;
			this.studentSum = studentSum;
			this.emptySum = emptySum;
		}

		// 다른 좌석과 비교
		@Override
		public int compareTo(Seat other) {
			// 인접 좋아하는 학생 수로 비교
			if (studentSum != other.studentSum)
				return -(studentSum - other.studentSum);

			// 인접 빈칸 수로 비교
			if (emptySum != other.emptySum)
				return -(emptySum - other.emptySum);

			// 행으로 비교
			if (x != other.x)
				return x - other.x;

			// 열로 비교
			return y - other.y;
		}
	}
}