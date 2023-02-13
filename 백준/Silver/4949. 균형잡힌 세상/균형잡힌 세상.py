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
            elif ch == '[':
                stack.append('[')
            elif ch == ']':
                if stack.pop() == '[':
                    continue
                return False

        except:
            return False

    if len(stack) == 0:
        return True
    return False





# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
while 1:
    stack = []
    string = input()
    if string == '.':
        break
    if check(string):
        print('yes')
    else:
        print('no')