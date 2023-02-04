from sys import stdin

n = int(stdin.readline())
board = [[-1] * 1001 for _ in range(1001)]
res = [0 for _ in range(n)]
minx, miny = 1001, 1001
maxx, maxy = 0, 0

for k in range(n):
    x, y, width, height = map(int, stdin.readline().split())
    for i in range(x, x + width):
        for j in range(y, y + height):
            board[i][j] = k
    minx, miny = min(x, minx), min(y, miny)
    maxx, maxy = max(x + width, maxx), max(y + height, maxy)

for k in range(n):
    for i in range(minx, maxx):
        for j in range(miny, maxy):
            if board[i][j] == k:
                res[k] += 1

for i in res:
    print(i)