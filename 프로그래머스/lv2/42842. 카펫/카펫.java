class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        
        for (int i = 3; i <= sum / 2; i++) {
            int a = sum / i;
            if (a * i == sum && (i - 2) * (a - 2) == yellow) {
                return new int[]{a, i};
            }
        }
        
        return new int[]{0, 0};
    }
}
