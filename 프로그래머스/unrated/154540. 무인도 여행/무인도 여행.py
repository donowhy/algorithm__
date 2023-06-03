from collections import deque

def solution(maps):
    N = len(maps)
    M = len(maps[0])
    visited = [[False for _ in range(M)] for _ in range(N)]
    res = []

    for i in range(N):
        for j in range(M):
            if maps[i][j] != 'X' and not visited[i][j]:
                res.append(bfs(i, j, maps, visited, N, M))
                
    res = sorted(res) if res else [-1]
    return res

def bfs(i, j, maps, visited, N, M):
    q = deque()
    cnt = int(maps[i][j])
    dx = [1, -1, 0, 0]
    dy = [0, 0, 1, -1]

    visited[i][j] = True
    q.append([i,j])

    while q:
        x, y = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < N and 0 <= ny < M and not visited[nx][ny] and maps[nx][ny] != 'X':
                visited[nx][ny] = True
                cnt += int(maps[nx][ny])
                q.append([nx,ny])

    return cnt
