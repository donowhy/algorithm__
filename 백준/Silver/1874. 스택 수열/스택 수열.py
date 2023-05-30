n = int(input())

lst = []
res = []

for i in range(n):
    tmp = int(input())
    res.append(tmp)

idx = 0

answer = []

for i in range(1, n+1):
    lst.append(i)
    answer.append('+')

    while lst and lst[-1] == res[idx]:
        idx += 1
        lst.pop()
        answer.append('-')

if idx == n:
    for i in range(len(answer)):
        print(answer[i])
else:
    print('NO')