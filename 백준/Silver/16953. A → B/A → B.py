a, b = map(int, input().split())
ans = 1
while (b != a):
    ans += 1
    res = b
    if b % 10 == 1:
        b //= 10
    elif b % 2 == 0:
        b //= 2

    if res == b:
        print(-1)
        break
else:
    print(ans)