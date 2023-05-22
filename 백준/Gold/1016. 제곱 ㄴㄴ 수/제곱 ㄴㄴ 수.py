n, m = map(int, input().split())
is_square = [False] * (m-n+1)

for i in range(2, int(m**0.5)+1):
    start = ((n-1)//(i*i) + 1) * i * i
    for j in range(start, m+1, i*i):
        is_square[j-n] = True

print(is_square.count(False))
