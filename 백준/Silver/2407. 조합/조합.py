n, k = map(int,input().split())

res = 1
dap = 1

for i in range(k):
    res *= (n - i)
    dap *= i + 1
ans = res // dap
print(ans)