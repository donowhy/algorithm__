import sys
sys.setrecursionlimit(10**6)

def dfs(start, graph, visited):
    stack = [start]
    count = 0
    while stack:
        current = stack.pop()
        for nxt in graph[current]:
            if not visited[nxt]:
                visited[nxt] = True
                stack.append(nxt)
                count += 1
    return count

n, m = map(int, input().split())
mid = (n // 2) + 1
light = [[] for _ in range(n+1)]
heavy = [[] for _ in range(n+1)]

for _ in range(m):
    a, b = map(int, input().split())
    light[b].append(a)
    heavy[a].append(b)

result = 0

for i in range(1, n+1):
    if dfs(i, light, [False] * (n+1)) >= mid or dfs(i, heavy, [False] * (n+1)) >= mid:
        result += 1

print(result)
