stk = set()

lett = input()

i = 0
while i < len(lett):
    for j in range(i+1, len(lett)+1):
        stk.add(lett[i:j])
    i += 1
res = stk
print(len(res))