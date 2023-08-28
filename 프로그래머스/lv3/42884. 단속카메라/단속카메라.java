import java.util.*;

class Solution {
    
    static class Point{
        int x;
        int y;
        
        Point (int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] routes) {
        // Convert the routes to a list of Points for clarity
        List<Point> pointList = new ArrayList<>();
        for (int[] route : routes) {
            pointList.add(new Point(route[0], route[1]));
        }
        
        // Sort points based on the end points (y-values in this case)
        pointList.sort((a, b) -> a.y != b.y ? Integer.compare(a.y, b.y) : Integer.compare(a.x, b.x));
        
        int count = 0;
        int camera = Integer.MIN_VALUE; // Initialize outside the minimum possible value
        
        for (Point point : pointList) {
            // Check if the current route's start is after the last camera's position
            if (camera < point.x) {
                count++;
                camera = point.y;
            }
        }

        return count;
    }
}
