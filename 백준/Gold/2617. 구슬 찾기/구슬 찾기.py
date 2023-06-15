import sys
input = sys.stdin.readline

n, m = map(int, input().split())
INF = int(1e9)
heavy = [[INF] * n for _ in range(n)]
light = [[INF] * n for _ in range(n)]

for _ in range(m):
    a, b = map(int, input().split())
    heavy[b - 1][a - 1] = 1  # b가 a보다 무겁다
    light[a - 1][b - 1] = 1  # a가 b보다 가볍다

for k in range(n):
    for i in range(n):
        for j in range(n):
            heavy[i][j] = min(heavy[i][j], heavy[i][k] + heavy[k][j])
            light[i][j] = min(light[i][j], light[i][k] + light[k][j])

cnt = 0
for i in range(n):
    heavy_cnt = len([1 for j in range(n) if heavy[i][j] != INF])
    light_cnt = len([1 for j in range(n) if light[i][j] != INF])
    if heavy_cnt > n // 2 or light_cnt > n // 2:
        cnt += 1

print(cnt)
