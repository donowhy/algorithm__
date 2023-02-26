n, m = map(int, input().split())
lst = list(map(int, input().split()))
lst.sort()

s = []

def dfs(start):
    if len(s) == m:
        print(' '.join(map(str,s)))
        return


    for i in lst:
        if i not in s:
            s.append(i)
            dfs(i)
            s.pop()

dfs(lst[0])