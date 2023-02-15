N = int(input())

letter = input()
num_list = []
stk = []
for i in range(N):
    num = int(input())
    num_list.append(num)

for i in letter:

    if 'A' <= i <= 'Z' :		
        stk.append(num_list[ord(i)-ord('A')])
    else:
        a = stk.pop()
        b = stk.pop()
        if i == '+':
            stk.append(b + a)
        elif i == '-':
            stk.append(b - a)
        elif i == '*':
            stk.append(b * a)
        elif i == '/':
            stk.append(b / a)
print('%.2f' %stk[0])