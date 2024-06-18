class Solution {
    public long solution(int k, int d) {
        long max = (long)Math.pow(d, 2);
        long answer = 0;
        
        for (int i = 0; i <= d; i += k) {
            long leftValue = max - (long)Math.pow(i, 2);
            long maxY = (long)Math.sqrt(leftValue);
            answer += (maxY / k) + 1; // maxY / k + 1이 가능한 y의 개수
        }
        
        return answer;
    }
}
