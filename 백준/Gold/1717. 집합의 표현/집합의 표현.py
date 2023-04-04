import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

p = []
def make_set():
    global p
    p = [i for i in range(N + 1)]

def find_set(x):
    if x == p[x]:
        return x
    
    p[x] = find_set(p[x])
    return p[x]

def union(x, y):
    x = find_set(x)
    y = find_set(y)
    if x < y:
        p[y] = x
    
    else:
        p[x] = y

def check(x, y):
    if find_set(x) == find_set(y):
        print('YES')
    else:
        print('NO')


N, M = map(int, input().split())

make_set()

for _ in range(M):
    q, x, y = map(int, input().split())
    if q:
        check(x, y)

    else:
        union(x, y)
