from collections import deque

for t in range(int(input())):
    lst = list(map(int,input().split()))
    lst.pop(0)
    dq = deque()
    cnt = 0
    for i in range(len(lst)):
        a = lst.pop(0)

        if dq:
            if a > dq[-1]:
                dq.append(a)
            elif a <= dq[-1]:
                for j in range(len(dq)):
                    if dq[j] < a:
                        # print(dq)
                        pass
                    elif dq[j] > a:
                        dq.insert(j, a)
                        cnt += len(dq) - j - 1
                        break

        else:
            dq.append(a)

    print(f'{t + 1} {cnt}')