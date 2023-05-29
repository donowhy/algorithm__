from collections import deque
import heapq

dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]

def bfs(x, y, size):
    visited = [[False]*n for _ in range(n)]
    queue = deque([(0, x, y)])
    heap = []
    visited[x][y] = True

    while queue:
        dist, x, y = queue.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny]:
                # 아기 상어가 지나갈 수 있는 경우
                if mapp[nx][ny] <= size:
                    visited[nx][ny] = True
                    queue.append((dist+1, nx, ny))
                    # 물고기를 먹을 수 있는 경우
                    if 0 < mapp[nx][ny] < size:
                        heapq.heappush(heap, (dist+1, nx, ny))
    if heap:
        return heapq.heappop(heap)
    else:
        return None

n = int(input())
mapp = [list(map(int, input().split())) for _ in range(n)]

x, y = 0, 0
for i in range(n):
    for j in range(n):
        if mapp[i][j] == 9:
            x, y = i, j
            mapp[i][j] = 0

time, size, feed = 0, 2, 0
while True:
    result = bfs(x, y, size)
    if result is None:
        print(time)
        break
    else:
        dist, x, y = result
        time += dist
        feed += 1
        mapp[x][y] = 0
        if feed == size:
            size += 1
            feed = 0
