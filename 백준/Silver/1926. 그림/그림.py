from collections import deque

def bfs(start, end):
    global cnt

    dx = (1, 0, -1, 0)
    dy = (0, 1, 0, -1)

    q = deque()
    q.append((start, end))
    mapp[start][end] = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 1 and mapp[nx][ny] == 0:
                q.append((nx,ny))
                cnt += 1
                mapp[nx][ny] = 1


n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]
mapp = [[0] * m for _ in range(n)]

mx = 0
ea = 0

for i in range(n):
    for j in range(m):
        if graph[i][j] == 1 and mapp[i][j] == 0:
            cnt = 1
            bfs(i,j)
            mx = max(cnt, mx)
            ea += 1

print(ea)
print(mx)
