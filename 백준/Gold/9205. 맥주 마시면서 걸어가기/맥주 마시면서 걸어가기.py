from collections import deque
import sys

def bfs(start, end):
    queue = deque()
    queue.append(start)
    visited = set()
    visited.add(start)

    while queue:
        x, y = queue.popleft()
        if (x, y) == end:
            return "happy"

        for nx, ny in coord:
            if (nx, ny) not in visited and abs(nx - x) + abs(ny - y) <= 1000:
                visited.add((nx, ny))
                queue.append((nx, ny))

    return "sad"

T = int(sys.stdin.readline())
for _ in range(T):
    n = int(sys.stdin.readline())
    coord = [tuple(map(int, sys.stdin.readline().split())) for _ in range(n+2)]
    print(bfs(coord[0], coord[-1]))
