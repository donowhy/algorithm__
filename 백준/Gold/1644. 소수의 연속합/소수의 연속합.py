
N = int(input())


isprime = [False] * 2 + [True] * (N - 1)

for i in range(2, int(N ** 0.5) + 1):
    if isprime[i] == True:
        for j in range(i + i, N + 1, i):
            isprime[j] = False

prime = []

for i in range(N + 1):
    if isprime[i] == True:
        prime.append(i)
        
res = 0
start = 0
end = 0
tmp = 0

while True:
    if tmp == N:
        res += 1

    if tmp > N:
        tmp -= prime[start]
        start += 1

    elif end == len(prime):
        break

    else:
        tmp += prime[end]
        end += 1

print(res)