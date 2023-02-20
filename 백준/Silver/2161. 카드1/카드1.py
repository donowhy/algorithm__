cards = int(input())

arr = []
stk = []

for i in range(1,cards+1):
    arr.append(i)

while len(arr) > 1:
    stk.append(arr.pop(0))
    arr.append(arr.pop(0))
if len(arr) == 1:
    stk.append(arr.pop(0))
print(*stk)
