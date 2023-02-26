n, m = map(int, input().split())
lst = sorted(list(map(int, input().split())))
s = []
lst.sort()

def dfs(start):
    if len(s) == m:
        print(' '.join(map(str, s)))
        return

    for i in range(start, n):
        s.append(lst[i])
        dfs(i)
        s.pop()


dfs(0)