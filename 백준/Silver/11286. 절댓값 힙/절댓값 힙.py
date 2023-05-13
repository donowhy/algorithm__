import heapq
import sys

input = sys.stdin.readline

N = int(input())
heap = []

for _ in range(N):
    num = int(input())
    if num != 0:
        heapq.heappush(heap, (abs(num), num))
    else:
        if heap:
            print(heapq.heappop(heap)[1])
        else:
            print(0)
