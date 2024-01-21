import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String[] solution(int[][] lines) {
        List<int[]> points = new ArrayList<>();
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        // 교점 찾기
        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                int[] point = findIntersection(lines[i], lines[j]);
                if (point != null) {
                    points.add(point);
                    minX = Math.min(minX, point[0]);
                    maxX = Math.max(maxX, point[0]);
                    minY = Math.min(minY, point[1]);
                    maxY = Math.max(maxY, point[1]);
                }
            }
        }

        // 별 그리기
        String[] answer = new String[maxY - minY + 1];
        for (int i = 0; i < answer.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j <= maxX - minX; j++) {
                sb.append('.');
            }
            answer[i] = sb.toString();
        }

        for (int[] point : points) {
            int x = point[0] - minX;
            int y = maxY - point[1];
            StringBuilder sb = new StringBuilder(answer[y]);
            sb.setCharAt(x, '*');
            answer[y] = sb.toString();
        }

        return answer;
    }
    
     private int[] findIntersection(int[] line1, int[] line2) {
        long A = line1[0], B = line1[1], E = line1[2];
        long C = line2[0], D = line2[1], F = line2[2];

        long denom = A * D - B * C;
        if (denom == 0) return null; // 평행하거나 일치하는 경우

        long xNumerator = B * F - E * D;
        long yNumerator = E * C - A * F;

        if (xNumerator % denom != 0 || yNumerator % denom != 0) return null; // 정수가 아닌 교점

        return new int[]{(int) (xNumerator / denom), (int) (yNumerator / denom)};
    }
}