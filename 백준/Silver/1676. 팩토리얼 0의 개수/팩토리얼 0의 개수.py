N = int(input())

ans = 1

for i in range(1,N + 1):
    ans *= i
str_i = str(ans)

cnt = 0

if str_i[-1] != '0':
    print(0)
else:
    for j in range(len(str_i)+1):
        if str_i[-j] == '0':
            cnt += 1
            if str_i[-j-1] == '0':
                continue
            else:
                break
    print(cnt)