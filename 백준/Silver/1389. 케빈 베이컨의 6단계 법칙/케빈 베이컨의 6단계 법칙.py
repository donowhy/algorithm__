from collections import deque

N, M = map(int, input().split())
adj = [[] for _ in range(N)]
for _ in range(M):
    a, b = map(lambda x: x - 1, map(int, input().split()))
    adj[a].append(b)
    adj[b].append(a)

kevin = []
ans = (-1, N * N)


def bfs(start, goal):
    chk = [False] * N
    chk[start] = True
    dq = deque()
    dq.append((start, 0))
    while dq:
        now, d = dq.popleft()
        if now == goal:
            return d

        nd = d + 1
        for nxt in adj[now]:
            if not chk[nxt]:
                chk[nxt] = True
                dq.append((nxt, nd))


def get_kevin(start):
    tot = 0
    for i in range(N):
        if i != start:
            tot += bfs(start, i)

    return tot


for i in range(N):
    kevin.append(get_kevin(i))

for i, v in enumerate(kevin):
    if ans[1] > v:
        ans = (i, v)

print(ans[0] + 1)
