import sys

input = sys.stdin.readline

N, M = map(int, input().split())

relationship = [[] for _ in range(N)]
visited = [False] * N
result = False

for _ in range(M):
    a, b = map(int, input().split())
    relationship[a].append(b)
    relationship[b].append(a)


def dfs(depth, x):
    global result
    if depth == 4:
        result = True
        return

    for i in relationship[x]:
        if not visited[i]:
            visited[i] = True
            dfs(depth + 1, i)
            visited[i] = False


for i in range(N):
    visited[i] = True
    dfs(0, i)
    visited[i] = False
    if result:
        break

if result:
    print(1)
else:
    print(0)