mod = 10_007 

dp = [0] * 1001 

n = int(input())

dp[1] = 1
dp[2] = 2
for i in range(3,1001):
    dp[i] = (dp[i - 1] + dp[i - 2]) % mod


print(dp[n])
