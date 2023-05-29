import sys
import heapq

tc = int(sys.stdin.readline())

for _ in range(tc):
    visited = [False] * 1_000_001
    min_h = []
    max_h = []

    cmd_num = int(sys.stdin.readline())

    for i in range(cmd_num):
        cmd = sys.stdin.readline().split()
        if cmd[0] == 'I':
            heapq.heappush(min_h, (int(cmd[1]), i))
            heapq.heappush(max_h, (-int(cmd[1]), i))
            visited[i] = True
        elif cmd[1] == '1':
            while max_h and not visited[max_h[0][1]]:
                _, idx = heapq.heappop(max_h)
            if max_h:
                visited[max_h[0][1]] = False
                heapq.heappop(max_h)
        else:
            while min_h and not visited[min_h[0][1]]:
                _, idx = heapq.heappop(min_h)
            if min_h:
                visited[min_h[0][1]] = False
                heapq.heappop(min_h)

    while min_h and not visited[min_h[0][1]]:
        heapq.heappop(min_h)
    while max_h and not visited[max_h[0][1]]:
        heapq.heappop(max_h)

    if min_h and max_h:
        print(-max_h[0][0], min_h[0][0])
    else:
        print("EMPTY")
