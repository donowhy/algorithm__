
from collections import deque

tc = int(input())

for _ in range(tc):
    commands = list(input())
    num = int(input())
    nums = input()[1:-1]

    if nums == '':
        arr = deque()
    else:
        arr = deque(map(int,nums.split(",")))

    doReverse = False
    flag = 1

    for command in commands:

        if command == "R":
            doReverse = not doReverse

        if command == "D":
            if arr:
                if doReverse == False:
                    arr.popleft()
                else:
                    arr.pop()
            else:
                flag = 0
                print("error")
                break
    if doReverse:
        arr.reverse()
    if flag:
        print(str(list(arr)).replace(' ', ''))