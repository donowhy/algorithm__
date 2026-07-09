import java.util.*;

class Solution {
    public int solution(String numbers) {
        String[] nums = numbers.split("");
        Set<Integer> numSet = new HashSet<>();

        dfs(nums, numSet, "", new boolean[nums.length]);

        int answer = 0;

        for (int num : numSet) {
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    private void dfs(String[] nums, Set<Integer> numSet, String current, boolean[] used) {
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;

                String next = current + nums[i];
                numSet.add(Integer.parseInt(next));

                dfs(nums, numSet, next, used);

                used[i] = false;
            }
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }
}