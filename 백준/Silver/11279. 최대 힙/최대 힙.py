from heapq import *
import sys

pq = []
for _ in range(int(sys.stdin.readline())):
    x = int(sys.stdin.readline())
    if x:
        heappush(pq, (-x, x))
    else:
        print(heappop(pq)[1] if pq else 0)