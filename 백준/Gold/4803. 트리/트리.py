import sys
sys.setrecursionlimit(10**6)

def dfs(node, parent):
    global is_tree
    visited[node] = True
    for nxt in graph[node]:
        if not visited[nxt]:
            dfs(nxt, node)
        elif visited[nxt] and nxt != parent: 
            is_tree = False

case = 0
while True:
    case += 1
    n, m = map(int, sys.stdin.readline().split())
    if n == 0 and m == 0:
        break

    graph = [[] for _ in range(n+1)]
    visited = [False for _ in range(n+1)]
    for _ in range(m):
        u, v = map(int, sys.stdin.readline().split())
        graph[u].append(v)
        graph[v].append(u)

    result = 0
    for i in range(1, n+1):
        if not visited[i]:
            is_tree = True
            dfs(i, 0)
            if is_tree:
                result += 1

    if result == 0:
        print('Case {}: No trees.'.format(case))
    elif result == 1:
        print('Case {}: There is one tree.'.format(case))
    else:
        print('Case {}: A forest of {} trees.'.format(case, result))
