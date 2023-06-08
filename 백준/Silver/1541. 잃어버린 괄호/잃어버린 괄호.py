s = input().split('-')
num_list = []

for i in s:
    cnt = 0
    s2 = i.split('+')
    for j in s2:
        cnt += int(j)
    num_list.append(cnt)

n = num_list[0]
for i in range(1, len(num_list)):
    n -= num_list[i]

print(n)
