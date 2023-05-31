import sys

n = int(sys.stdin.readline())
sequence = list(map(int, sys.stdin.readline().split()))
dp = [[0]*n for _ in range(n)]

for i in range(n):
    dp[i][i] = 1

for i in range(n-1, -1, -1):
    for j in range(i+1, n):
        if sequence[i] == sequence[j]:
            if j-i == 1 or dp[i+1][j-1]:
                dp[i][j] = 1

m = int(sys.stdin.readline())
for _ in range(m):
    s, e = map(int, sys.stdin.readline().split())
    print(dp[s-1][e-1])
