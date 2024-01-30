import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    static int rSize, cSize;
    static int[][] fragments;
    static int fragmentIdx = 1;
    static Map<Integer, Integer> fragmentsInfo = new HashMap<>();
    static boolean[][] visited;
    static int[] dirR = {-1, 1, 0, 0};
    static int[] dirC = {0, 0, -1, 1};

    public int solution(int[][] land) {
        int answer = 0;
        rSize = land.length;
        cSize = land[0].length;
        fragments = new int[rSize][cSize];
        visited = new boolean[rSize][cSize];

        for (int r = 0; r < rSize; r++) {
            for (int c = 0; c < cSize; c++) {
                if (visited[r][c] || land[r][c] == 0) {
                    continue;
                }

                initFragment(land, r, c);
            }
        }

        for (int c = 0; c < cSize; c++) {
            Set<Integer> fragmentTypes = new HashSet<>();
            int tmpMaxAmount = 0;
            for (int r = 0; r < rSize; r++) {
                if (fragments[r][c] > 0) {
                    fragmentTypes.add(fragments[r][c]);
                }
            }

            for (Integer frg : fragmentTypes) {
                tmpMaxAmount += fragmentsInfo.get(frg);
            }

            answer = Math.max(answer, tmpMaxAmount);
        }

        return answer;
    }

    static void initFragment(int[][] land, int r, int c) {
        visited[r][c] = true;
        fragments[r][c] = fragmentIdx;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});
        int size = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            size++;

            for (int d = 0; d < 4; d++) {
                int nxtR = cur[0] + dirR[d];
                int nxtC = cur[1] + dirC[d];

                if (nxtR < 0 || nxtR >= rSize || nxtC < 0 || nxtC >= cSize || visited[nxtR][nxtC]
                        || land[nxtR][nxtC] == 0) {
                    continue;
                }

                visited[nxtR][nxtC] = true;
                fragments[nxtR][nxtC] = fragmentIdx;
                q.add(new int[]{nxtR, nxtC});
            }
        }

        fragmentsInfo.put(fragmentIdx++, size);
    }
}