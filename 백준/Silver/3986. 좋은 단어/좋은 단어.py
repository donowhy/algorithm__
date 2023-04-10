n = int(input())
cnt = 0
for _ in range(n):
    stk = []
    lst = list(input())
    for i in lst:
        if not stk:
            stk.append(i)

        elif stk and i != stk[-1] :
            stk.append(i)

        elif i == stk[-1]:
            stk.pop()

    if len(stk) == 0 :
        cnt += 1

print(cnt)