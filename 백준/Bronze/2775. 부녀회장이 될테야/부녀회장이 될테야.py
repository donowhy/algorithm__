t = int(input())

dp = [[0] * 15 for _ in range(15)]
dp[0] = [x for x in range(15)]

for i in range(1,15):
    for j in range(1, 15):
        for k in range(1, j + 1):
            dp[i][j] += dp[i-1][k]


for _ in range(t):
    k, n = int(input()), int(input())
    print(dp[k][n])