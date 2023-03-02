T = int(input())
lst = [[0] * 101 for _ in range(101)]
for z in range(1, T + 1):
    N, M = map(int, input().split())
    for i in range(N, N + 10):
        for j in range(M, M + 10):
            lst[i][j] = 1
cnt = 0
for i in range(101):
    for j in range(100):
        if lst[i][j] + lst[i][j+1] == 1:
            cnt += 1

        if lst[j][i] + lst[j+1][i] == 1:
            cnt += 1

print(cnt)