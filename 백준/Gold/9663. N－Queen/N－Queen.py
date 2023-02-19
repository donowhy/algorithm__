N = int(input())

def dfs(n):
    global ans
    if n == N: # N행까지 진행한 경우 경우의 수 가능
        ans += 1
        return

    for j in range(N):
        if v1[j] == v2[n+j] == v3[n-j] == 0: #열/ 대각선 모두 Q없는 경우
            v1[j] = v2[n + j] = v3[n - j] = 1
            dfs(n+1)
            v1[j] = v2[n + j] = v3[n - j] = 0

ans = 0
v1 = [0] * N
v2 = [0] * (2 * N)
v3 = [0] * (2 * N)

dfs(0)
print(ans)