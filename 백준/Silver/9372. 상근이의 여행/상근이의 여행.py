import sys

def dfs(node, cnt):
    check[node] = 1
  #  print(check)
    for n in graph[node]:
        if check[n] == 0:
            cnt = dfs(n, cnt +1)
    return cnt

for _ in range(int(sys.stdin.readline())):
    N, M = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range( N +1)]
    for _ in range(M):
        u, v = map(int, sys.stdin.readline().split())
        graph[u].append(v)
        graph[v].append(u)
   # print(graph)
    check = [0 ] *( N +1)
    cnt = dfs(1, 0)
    print(cnt)