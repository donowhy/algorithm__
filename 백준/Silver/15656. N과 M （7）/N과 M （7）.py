n, m = map(int, input().split())
lst = sorted(list(map(int, input().split())))
s = []
lst.sort()

def dfs(start):
    if len(s) == m:
        print(' '.join(map(str, s)))
        return

    for i in lst:
        s.append(i)
        dfs(i)
        s.pop()

dfs(lst[0])
