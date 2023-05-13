
N = int(input())
lst = []
for _ in range(N):
    lst.append(int(input()))

lst = lst[::-1]
cnt = 0

for i in range(N - 1):
    if lst[i] <= lst[i+1]:
        tmp = lst[i] - 1
        cnt += lst[i+1] - tmp
        lst[i+1] = tmp

print(cnt)