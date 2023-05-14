N, M = map(int,input().split())
lst = sorted(list(map(int,input().split())))

while M > 0:
    tmp = lst[0] + lst[1]
    lst[0] = tmp
    lst[1] = tmp
    lst.sort()
    M -= 1

print(sum(lst))
