from collections import deque
import sys
input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def bfs():
    while queue:
        a, b = queue.popleft()
        for i in range(4):
            x = a + dx[i]
            y = b + dy[i]
            if 0 <= x < n and 0 <= y < m and tomato[x][y] == 0:
                tomato[x][y] = tomato[a][b] + 1
                queue.append([x, y])

m, n = map(int, input().split())
tomato = []
queue = deque()

for i in range(n):
    row = list(map(int, input().split()))
    for j in range(m):
        if row[j] == 1:
            queue.append([i, j])
    tomato.append(row)

bfs()

max_day = 0
for i in tomato:
    for j in i:
        if j == 0:
            print(-1)
            exit(0)
    max_day = max(max_day, max(i))

print(max_day - 1)
