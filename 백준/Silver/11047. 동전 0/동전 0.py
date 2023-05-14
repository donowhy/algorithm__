N, M = map(int,input().split())

my = sorted(list(int(input()) for _ in range(N)), reverse=True)

cnt = 0
i = 0
while M > 0:
    if M // my[i] > 0:
        tmp = M // my[i]
        cnt += tmp
        M -= (my[i] * tmp)

    i += 1

print(cnt)