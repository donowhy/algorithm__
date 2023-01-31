N, K = map(int, input().split())
 
g = [0] * 1000
b = [0] * 1000
 
for i in range(N):
    S, G = map(int, input().split())
    if S == 0:
        g[G] += 1
    else:
        b[G] += 1
        
for i in range(1, 7):
    if (g[i] % K == 0):
        g[i] = g[i] // K
    else:
        g[i] = g[i] // K + 1
 
    if (b[i] % K == 0):
        b[i] = b[i] // K
    else:
        b[i] = b[i] // K + 1
 
        
result = sum(g) + sum(b)
print(result)