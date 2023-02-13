mod = 10_007

dp = [0] * 1001

n = int(input())

dp[1] = 1
dp[2] = 3
dp[3] = 5
for i in range(4,1001):
    dp[i] = (dp[i - 1] + dp[i - 2]*2) % mod


print(dp[n])