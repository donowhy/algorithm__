from collections import deque

def bfs(start, end):
    dx = (1, 0, -1, 0)
    dy = (0, 1, 0, -1)

    q = deque()
    q.append((start, end))

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < m and mapp[nx][ny] == 1:
                q.append((nx,ny))
                mapp[nx][ny] = mapp[x][y] + 1

n, m = map(int, input().split())
mapp = [list(map(int, input())) for _ in range(n)]

bfs(0,0)
print(mapp[n-1][m-1])
