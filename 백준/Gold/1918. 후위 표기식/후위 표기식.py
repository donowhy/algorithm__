letter = list(input())
stk = []
res = ''

for i in letter:
    if i.isalpha():
        res += i
    else:
        if i == '(':
            stk.append(i)
        elif i == '*' or i == '/':
            while stk and (stk[-1] == '*' or stk[-1] == '/'):
                res += stk.pop()
            stk.append(i)
        elif i == '+' or i == '-':
            while stk and stk[-1] != '(':
                res += stk.pop()
            stk.append(i)
        elif i == ')':
            while stk and stk[-1] != '(':
                res += stk.pop()
            stk.pop()
while stk:
    res += stk.pop()
print(res)