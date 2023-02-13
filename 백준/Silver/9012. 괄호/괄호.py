def check(string):
    global stack

    for ch in string:
        try:
            if ch == '(':
                stack.append('(')
            elif ch == ')':
                if stack.pop() == '(':
                    continue
                return False
        except:
            return False
    if len(stack) == 0:
        return True
    return False


T = int(input())
for test_case in range(1, T + 1):
    stack = []
    string = input().replace(' ','')
    if check(string):
        print('YES')
    else:
        print('NO')