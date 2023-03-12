a, b = map(int,input().split())

lst = list(map(int,input().split()))
arr = [0]
hp = 0
for i in lst:
    hp += i
    arr.append(hp)

res = []
for i in range(b,len(arr)):
    res.append(arr[i] - arr[i-b])

mx = max(res)

ct = res.count(mx)
if mx:
    print(mx,'\n',ct, sep='')
else:
    print('SAD')