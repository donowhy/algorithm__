from collections import deque

def bfs():
    dq = deque()
    dq.append(n)
    while dq:
        x = dq.popleft()
        if x == k:
            print(arr[x])
            break
        for nx in (x-1, x+1, x*2):
            if 0 <= nx <= max and not chk[nx]:
                if nx == 2 * x:
                    arr[nx] = arr[x]
                    chk[nx] = 1
                    dq.appendleft(nx)
                elif nx + 1 <= max and not chk[nx]:
                    arr[nx] = arr[x] + 1
                    chk[nx] = 1
                    dq.append(nx)
                elif nx -1 <= max and not chk[nx]:
                    arr[nx] = arr[x] + 1
                    chk[nx] = 1
                    dq.append(nx)
max = 10 ** 5
arr = [0] * (max + 1)
chk = [0] * (max + 1)
n, k = map(int,input().split())

bfs()