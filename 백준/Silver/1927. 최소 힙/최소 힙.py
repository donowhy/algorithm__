from heapq import *
import sys

pq = []
for _ in range(int(sys.stdin.readline().rstrip())):
    x = int(sys.stdin.readline().rstrip())
    if x:
        heappush(pq, x)
    else:
        print(heappop(pq) if pq else 0)