N = int(input())
M = int(input())

arr = list(input())
stk = []
cnt = 0
for i in range(len(arr) - (N*2)):
    if ''.join(arr[i:i+(N*2+1)]) == 'I' + 'OI' * N:
        cnt += 1
print(cnt)