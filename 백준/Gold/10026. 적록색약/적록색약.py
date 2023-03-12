from collections import deque

def bfs(x,y):
    dq.append((x,y))
    dx = [1,0,-1,0]
    dy = [0,1,0,-1]
    visited[x][y] = 1
    while dq:
        x, y = dq.popleft()
        for d in range(4):
            nx = x + dx[d]
            ny = y + dy[d]
            if 0<=nx<N and 0<=ny<N and a[nx][ny] == a[x][y] and not visited[nx][ny]:
                visited[nx][ny] = 1 
                dq.append((nx,ny))


N = int(input())
a = [list(input()) for _ in range(N)]
dq = deque()

visited = [[0] * N for _ in range(N)]
cnt1 = 0
for i in range(N):
    for j in range(N):
        if not visited[i][j]:
            bfs(i,j)
            cnt1 += 1

for i in range(N):
    for j in range(N):
        if a[i][j] == 'G':
            a[i][j] = 'R'

visited = [[0] * N for _ in range(N)]
cnt2 = 0
for i in range(N):
    for j in range(N):
        if not visited[i][j]:
            bfs(i,j)
            cnt2 += 1

print(cnt1, cnt2)
