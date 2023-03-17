from heapq import *
import sys

l_pq = []
r_pq = []
ans = []
for _ in range(int(sys.stdin.readline().rstrip())):
    x = int(sys.stdin.readline().rstrip())
    if len(l_pq) <= len(r_pq):
        heappush(l_pq,(-x,x))
    else:
        heappush(r_pq,(x,x))
    # print(l_pq, "왼쪽")
    # print(r_pq, "오른쪽")
    if r_pq and l_pq[0][1] > r_pq[0][1]:
        mn = heappop(r_pq)[1]
        mx = heappop(l_pq)[1]
        heappush(l_pq,(-mn,mn))
        heappush(r_pq,(mx,mx))
        # print(l_pq)
    ans.append(l_pq[0][1])

for i in ans:
    print(i)