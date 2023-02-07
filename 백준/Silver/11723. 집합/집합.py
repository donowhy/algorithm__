import sys

S = set()
T = int(sys.stdin.readline())
for i in range(T):
    cmdAndX = sys.stdin.readline().split()
    if len(cmdAndX) == 1:
        if cmdAndX[0] == 'all':
            S = set([x for x in range(1, 21)])
        else:
            S = set()

    else:
        cmd, x = cmdAndX[0], int(cmdAndX[1])
        if cmd == 'add':
            S.add(x)
        elif cmd == 'check':
            if x in S:
                print(1)
            else:
                print(0)
        elif cmd == 'remove':
            if x in S:
                S.discard(x)
        elif cmd == 'toggle':
            if x in S:
                S.discard(x)
            else:
                S.add(x)