t = int(input())

dp = [0] * 1000001

dp[1] = 1
dp[2] = 2
dp[3] = 4
for k in range(t):
    n =int(input())
    for i in range(4,n+1):
        dp[i] = (dp[i-1] + dp[i-2] + dp[i-3]) % 1000000009
    print(dp[n])