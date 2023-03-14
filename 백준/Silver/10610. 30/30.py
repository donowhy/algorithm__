T = list(input())
T.sort()

hap = 0
for i in T:
    hap += int(i)

T.reverse()
if int(''.join(T)) % 30:
    print(-1)
else:
    print(''.join(T))