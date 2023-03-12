import sys


def solution(n, graph):
    dp = [[0] * 3 for _ in range(n)]

    dp[1][0] = graph[1][0] + graph[0][1]
    dp[1][1] = graph[1][1] + min(dp[1][0], graph[0][1], graph[0][2]+graph[0][1])
    dp[1][2] = graph[1][2] + min(dp[1][1], graph[0][1], graph[0][1] + graph[0][2])

    for i in range(2, n):
        for j in range(3):
            if j == 0:
                min_value = min(dp[i - 1][j], dp[i - 1][j + 1])
            elif j == 1:
                min_value = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1], dp[i][j - 1])
            else:
                min_value = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1])

            dp[i][j] = min_value + graph[i][j]

    print(f'{tc}. {dp[-1][1]}')
tc = 0
while True:
    a = int(input())
    if a == 0:
        break
    b = [list(map(int, sys.stdin.readline().split())) for _ in range(a)]
    tc += 1
    solution(a, b)