import sys
import heapq


heap = []
input = sys.stdin.readline
n = int(input())
arr = []
for i in range(n):
    a, b = map(int, input().split())
    arr.append([a, b])
arr.sort(key=lambda x: x[0])
heapq.heappush(heap, arr[0][1])
for i in range(1, n):
    if heap[0] > arr[i][0]:
        heapq.heappush(heap, arr[i][1])
    else:
        heapq.heappop(heap)
        heapq.heappush(heap, arr[i][1])
print(len(heap))