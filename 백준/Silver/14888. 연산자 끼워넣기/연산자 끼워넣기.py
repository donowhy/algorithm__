def dfs(n, num, add, sub, mul, div):
    global mn, mx
    if n == N:
        mn = min(mn, num)
        mx = max(mx, num)

    if add: dfs(n + 1, num + arr[n], add - 1, sub, mul, div)
    if sub: dfs(n + 1, num - arr[n], add, sub - 1, mul, div)
    if mul: dfs(n + 1, num * arr[n], add, sub, mul - 1, div)
    if div: dfs(n + 1, int(num / arr[n]), add, sub, mul, div - 1)

N = int(input())
arr =  list(map(int, input().split()))
add, sub, mul, div = map(int, input().split())  # (+, -, *, //)

mn = 10 ** 8
mx = - 10 ** 8
dfs(1, arr[0], add, sub, mul, div)
print(f'{mx} \n{mn}')