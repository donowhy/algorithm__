class Solution {
    public int solution(int n, long l, long r) {
        return countOnes(n, l, r);
    }

    // n 단계 길이의 칸토어 집합에서 [l,r] 구간의 1 개수
    private int countOnes(long n, long l, long r) {
        if (n == 0) return 1; // 0단계는 1 하나뿐
        
        long len = (long) Math.pow(5, n-1); // n단계 길이를 5^(n-1) 단위로 쪼갬
        
        int cnt = 0;
        for (int i = 0; i < 5; i++) {
            long start = i * len + 1;
            long end = (i + 1) * len;
            
            if (i == 2) continue; // 가운데 구간 제외
            
            if (r < start || l > end) continue; // 겹치지 않으면 스킵
            
            long nl = Math.max(l, start) - start + 1;
            long nr = Math.min(r, end) - start + 1;
            cnt += countOnes(n-1, nl, nr);
        }
        return cnt;
    }
}
