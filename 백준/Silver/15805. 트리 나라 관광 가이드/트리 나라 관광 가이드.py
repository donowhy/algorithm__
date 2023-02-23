import sys
input=sys.stdin.readline


N=int(input())
L=list(map(int,input().split()))

visit=set()
visit.add(L[0])
dp=[0]*(200001)
dp[L[0]]=-1

for i in range(1,N):
    if L[i] not in visit:
        dp[L[i]]=L[i-1]
        visit.add(L[i])

print(len(visit))
for i in range(len(visit)):
    print(dp[i] , end=" ")