N, M = map(int, input().split())
L = list(map(int, input().split()))

L.sort()
visited = [False] * N
out = []

def solve(depth, N, M):
    if depth == M:
        print(' '.join(map(str, out)))
        return
    overlap = 0
    for i in range(N):
        if not visited[i] and overlap != L[i]:
            visited[i] = True
            out.append(L[i])
            overlap = L[i]
            solve(depth+1, N, M)
            visited[i] = False
            out.pop()

solve(0, N, M)