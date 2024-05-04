import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};  // 방향 벡터: 우, 하, 좌, 상

    static class Cleaner {
        int x;
        int y;

        public Cleaner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static ArrayList<Cleaner> cleaners = new ArrayList<>();  // 공기청정기 위치 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int r = Integer.parseInt(s[0]);
        int c = Integer.parseInt(s[1]);
        int t = Integer.parseInt(s[2]);

        int[][] map = new int[r][c];  // 방의 미세먼지 상태를 나타내는 2차원 배열

        for (int i = 0; i < r; i++) {
            List<Integer> list = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());
            for (int j = 0; j < c; j++) {
                map[i][j] = list.get(j);
                if (list.get(j) == -1) {
                    cleaners.add(new Cleaner(i, j));
                }
            }
        }

        for (int i = 0; i < t; i++) {
            spread(map, r, c);  // 미세먼지 확산 함수 호출
            sweepAir(map, r, c);  // 공기청정기 작동 함수 호출
        }

        System.out.println(getTotalDust(map));
    }

    // 미세먼지 확산
    static void spread(int[][] map, int r, int c) {
        int[][] tempMap = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;
                    int count = 0;

                    for (int[] dir : directions) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];

                        if (nx >= 0 && nx < r && ny >= 0 && ny < c && map[nx][ny] != -1) {
                            tempMap[nx][ny] += spreadAmount;
                            count++;
                        }
                    }

                    map[i][j] -= spreadAmount * count;
                }
            }
        }

        // 확산된 미세먼지 결과를 원본 맵에 적용
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] += tempMap[i][j];
            }
        }
    }

    // 공기청정기 작동
    static void sweepAir(int[][] map, int r, int c) {
        upStream(map, r, c);
        downStream(map, r, c);
    }
    // 상단 공기청정기에서 공기를 위로 순환시키는 로직
    static void upStream(int[][] map, int r, int c) {
        Cleaner cleaner = cleaners.get(0);
        // 반시계 방향으로 순환
        // 위로 이동
        for (int i = cleaner.x - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        // 왼쪽으로 이동
        for (int i = 0; i < c - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        // 아래로 이동
        for (int i = 0; i < cleaner.x; i++) {
            map[i][c - 1] = map[i + 1][c - 1];
        }
        // 오른쪽으로 이동
        for (int i = c - 1; i > 1; i--) {
            map[cleaner.x][i] = map[cleaner.x][i - 1];
        }
        // 공기청정기에서 나온 공기는 청정
        map[cleaner.x][1] = 0;
    }

    // 하단 공기청정기에서 공기를 아래로 순환시키는 로직
    static void downStream(int[][] map, int r, int c) {
        Cleaner cleaner = cleaners.get(1);
        // 시계 방향으로 순환
        // 아래로 이동
        for (int i = cleaner.x + 1; i < r - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        // 왼쪽으로 이동
        for (int i = 0; i < c - 1; i++) {
            map[r - 1][i] = map[r - 1][i + 1];
        }
        // 위로 이동
        for (int i = r - 1; i > cleaner.x; i--) {
            map[i][c - 1] = map[i - 1][c - 1];
        }
        // 오른쪽으로 이동
        for (int i = c - 1; i > 1; i--) {
            map[cleaner.x][i] = map[cleaner.x][i - 1];
        }
        // 공기청정기에서 나온 공기는 청정
        map[cleaner.x][1] = 0;
    }


    // 총 미세먼지 양 계산
    static int getTotalDust(int[][] map) {
        int totalDust = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > 0) {
                    totalDust += map[i][j];
                }
            }
        }
        return totalDust;
    }
}
