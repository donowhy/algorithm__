N = int(input())

stk = []

for i in range(1, N+1):
    num = int(input())
    if num != 0:
        stk.append(num)
    elif num == 0:
        stk.pop()

hap = 0
if len(stk) != 0:
    for i in range(len(stk)):
        hap += stk[i]
    print(hap)
else:
    print(0)
