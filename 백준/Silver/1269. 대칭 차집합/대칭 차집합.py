A_count, B_count = map(int,input().split())
A = list(map(int,input().split()))
B = list(map(int,input().split()))

A_B = list(set(A)-set(B))
B_A = list(set(B)-set(A))
print(len(A_B) + len(B_A))\

