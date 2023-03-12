from itertools import permutations

n = int(input())
lst = list(input().split())

arr = []

for i in range(0,10):
    arr.append(i)

combi = list(permutations(arr,n+1))

ans = []

for i in combi:
    if ans:
        break
    for j in range(n):

        if j == n-1:
            if lst[j] == '<':
                if i[j] < i[j + 1]:
                    ans.append(i)


            elif lst[j] == '>':
                if i[j] > i[j + 1]:
                    ans.append(i)
        else:
            if lst[j] == '<':
                if i[j] < i[j+1]:
                    continue
                else:
                    break

            elif lst[j] == '>':
                if i[j] > i[j+1]:
                    continue
                else:
                    break
stk = []
for i in range(len(combi)-1,0,-1):
    if stk:
        mx = list(stk[0])
        mn = list(ans[0])

        print(*mx, "\n", *mn, sep='')
        exit()
    for j in range(n):
        if j == n-1:
            if lst[j] == '<':
                if combi[i][j] < combi[i][j + 1]:
                    stk.append(combi[i])


            elif lst[j] == '>':
                if combi[i][j] > combi[i][j + 1]:
                    stk.append(combi[i])
        else:
            if lst[j] == '<':
                if combi[i][j] < combi[i][j+1]:
                    continue
                else:
                    break

            elif lst[j] == '>':
                if combi[i][j] > combi[i][j+1]:
                    continue
                else:
                    break
