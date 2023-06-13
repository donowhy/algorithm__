n = int(input())

minus = []
plus = []
hap = 0

for _ in range(n):
    tmp = int(input())
    if tmp <= 0:
        minus.append(tmp)
    elif tmp == 1:
        hap += 1
    else:
        plus.append(tmp)


plus.sort(reverse=True)
minus.sort()

stk = []

for i in range(len(minus)):
    stk.append(minus[i])

    if len(stk) == 2:
        hap += (stk[0] * stk[1])

        stk.clear()

if len(stk) == 1:
    tmp = stk.pop()
    hap += tmp

for i in range(len(plus)):
    stk.append(plus[i])

    if len(stk) == 2:
        hap += (stk[0] * stk[1])
        stk.clear()

if len(stk) == 1:
    hap += stk[0]

print(hap)
