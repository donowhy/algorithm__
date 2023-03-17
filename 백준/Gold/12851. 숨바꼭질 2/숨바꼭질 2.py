
from collections import deque


def bfs(n):
    dq = deque([n])
    chk[n][0] = 0
    chk[n][1] = 1

    while dq:
        x = dq.popleft()

        for i in [x - 1, x + 1, x * 2]:
            if 0 <= i <= max:
                if chk[i][0] == -1:
                    chk[i][0] = chk[x][0] + 1  
                    chk[i][1] = chk[x][1] 
                    dq.append(i)

                elif chk[i][0] == chk[x][0] + 1:
                    chk[i][1] += chk[x][1]  

max = 10 ** 5
n, k = map(int, input().split())
chk = [[-1, 0] for _ in range(max + 1)]  

bfs(n)
print(chk[k][0])
print(chk[k][1])