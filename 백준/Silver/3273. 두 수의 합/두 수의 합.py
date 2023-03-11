T = int(input())

lst = list(map(int,input().split()))
wnt = int(input())

lst.sort()
start = 0
end = len(lst) - 1
cnt = 0

while True:
    if lst[start] + lst[end] > wnt:
        end -= 1

    elif lst[start] + lst[end] < wnt:
        start += 1

    elif lst[start] + lst[end] == wnt:
        cnt += 1
        start += 1
        end -= 1

    if start >= end:
        break
print(cnt)