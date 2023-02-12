lst = list(input())

lst.sort(reverse=True)

for i in range(len(lst)):
    print(lst[i], sep = '', end='')