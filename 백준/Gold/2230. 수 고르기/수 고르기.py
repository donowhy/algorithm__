N, M = map(int,input().split())

lst = []

for _ in range(N):
    lst.append(int(input()))

lst.sort()

start = 0
end = 0
mn = 10 ** 10

while end < N:
    try : 
        if M <= lst[end] - lst[start]:
            mn = min(lst[end] - lst[start], mn)
            start += 1
        else:
            end += 1
    except:
        break
print(mn)
