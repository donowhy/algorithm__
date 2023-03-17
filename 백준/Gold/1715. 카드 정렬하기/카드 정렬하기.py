from heapq import *
import sys

pq = []
for _ in range(int(sys.stdin.readline().rstrip())):
    x = int(sys.stdin.readline().rstrip())
    heappush(pq, x)
hap = 0
while True:
    if len(pq) >= 2:
        a = heappop(pq)
        b = heappop(pq)
        hap += (a + b)
        heappush(pq, a + b)

    if len(pq) == 1:
        print(hap)
        break