L = int(input())
N = int(input())
ans = []
check = [False] * (L + 1)

for i in range(1, N + 1):
    P, K = map(int, input().split())
    ans.append(K - P + 1)
    for j in range(P, K+1):
        if check[j] == False:
            check[j] = i

res =[]

for j in range(1, N+1):
    hap = 0
    for i in range(len(check)):
        if check[i] == j:
            hap += 1
    res.append(hap)
print(ans.index(max(ans))+1)
print(res.index(max(res))+1)