import sys
import heapq

input = sys.stdin.readline
dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def dijkstra():
    heap = []
    heapq.heappush(heap, (0, 0, 0))
    dist = [[float('inf')] * m for _ in range(n)]
    dist[0][0] = 0

    while heap:
        cost, x, y = heapq.heappop(heap)

        if x == n - 1 and y == m - 1:
            return cost

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]

            if 0 <= nx < n and 0 <= ny < m:
                next_cost = cost + graph[nx][ny]

                if next_cost < dist[nx][ny]:
                    dist[nx][ny] = next_cost
                    heapq.heappush(heap, (next_cost, nx, ny))

m, n = map(int, input().split())
graph = [list(map(int, input().strip())) for _ in range(n)]

print(dijkstra())
