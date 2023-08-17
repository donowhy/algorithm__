h, w, n, m = map(int,input().split())


a = (h-1) // (n+1) + 1
b = (w-1) // (m+1) + 1

print(a * b)