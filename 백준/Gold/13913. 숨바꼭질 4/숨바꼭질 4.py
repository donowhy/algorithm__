from collections import deque

def bfs():
    q = deque([n])
    while q:
        x = q.popleft()
        if x == k:
            return array[x]
        for nx in (x-1, x+1, x*2):
            if 0 <= nx < MAX and array[nx] == 0:
                array[nx] = array[x] + 1
                q.append(nx)
                path[nx] = x

n, k = map(int, input().split())
MAX = 10**6
array = [0]*MAX
path = [0]*MAX

result = bfs()

print(result)
p = []
x = k
while x != n:
    p.append(x)
    x = path[x]
p.append(n)
p = p[::-1]

for i in p:
    print(i, end=' ')
