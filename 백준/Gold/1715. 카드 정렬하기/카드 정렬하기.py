import heapq as hq

N = int(input())

ca = []
for _ in range(N):
    hq.heappush(ca, int(input()))

tmp = 0

while len(ca) > 1:
    a = hq.heappop(ca)
    b = hq.heappop(ca)
    tmp += (a+b)
    hq.heappush(ca, (a+b))


print(tmp)