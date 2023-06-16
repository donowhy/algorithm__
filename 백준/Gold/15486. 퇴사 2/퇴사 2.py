N = int(input())

T, P = [0]*N, [0]*N

dp = [0]*(N+1)  
for i in range(N):
    T[i], P[i] = map(int, input().split())

for i in range(N-1, -1, -1): 
    if T[i] + i <= N: 
        dp[i] = max(P[i] + dp[i + T[i]], dp[i+1]) 
    else:  
        dp[i] = dp[i+1]

print(dp[0]) 