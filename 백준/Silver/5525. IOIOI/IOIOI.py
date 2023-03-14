N = int(input())
M = int(input())

arr = list(input())
stk = []
cnt = 0
a = 'I' + 'OI' * N
for i in range(len(arr) - (N*2)):
    if ''.join(arr[i:i+(N*2+1)]) == a:
        cnt += 1
print(cnt)