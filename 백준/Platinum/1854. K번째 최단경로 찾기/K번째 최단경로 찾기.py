import heapq as hq

def dijk(start):
    p = []
    hq.heappush(p, (0,start))
    res[1][0] = 0

    while p:
        cost, node = hq.heappop(p)
        for nNode, nCost in mapp[node]:
            sCost = cost + nCost
            if res[nNode][k-1] > sCost:
                res[nNode][k-1] = sCost
                res[nNode].sort()
                hq.heappush(p, (sCost, nNode))


n, m, k = map(int,input().split())

inf = 10 ** 9

mapp = [[] for _ in range(n+1)]
res = [[inf] * k for _ in range(n+1)]

for i in range(m):
    start, end, hour = map(int,input().split())
    mapp[start].append([end, hour])

dijk(1)

for i in range(1, n+1):
    if res[i][k-1] == inf:
        print(-1)
    else:
        print(res[i][k-1])


