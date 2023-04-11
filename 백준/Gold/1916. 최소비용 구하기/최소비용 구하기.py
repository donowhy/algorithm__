import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

n = int(input())
m = int(input())

g = [[] for _ in range(n+1)]
for i in range(m):
    a, b, w = map(int, input().split())
    g[a].append((b, w))

st, ed = map(int, input().split())

dist = [INF]*(n+1)
def dijkstra(start):
    dist[start] = 0
    q = [(0, st)]

    while q:
        w, cur = heapq.heappop(q)
        if dist[cur] < w: #이미 처리되었다면 무시
            continue

        for dest, wei in g[cur]:
            cost = dist[cur] + wei
            if dist[dest] > cost:
                dist[dest] = cost
                heapq.heappush(q, (cost, dest))

dijkstra(st)
print(dist[ed])