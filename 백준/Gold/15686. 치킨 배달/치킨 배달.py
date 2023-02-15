from itertools import combinations
N, M = map(int,input().split())

arr = []
chik = []

for i in range(N):
    for j, v in enumerate(map(int,input().split())):
        if v == 1:
            arr.append((i, j))
        elif v == 2:
            chik.append((i, j))

def get_dist(a, b):
    return abs(a[0] - b[0]) + abs(a[1] - b[1])


ans = 2 * N * len(arr)
for combi in combinations(chik, M):
    tot = 0 
    for arrs in arr:
        tot += min(get_dist(arrs, chic) for chic in combi)


    ans = min(ans, tot)

print(ans)

