def dfs(idx, arr, letter):
    if len(letter) == L:
        cnt = [l for l in letter if l in ja]
        if len(cnt) >= 1 and len(letter)-len(cnt) >= 2:
            arr.append(letter)
            return

    for i in range(idx, C):
        dfs(i+1, arr, letter+lst[i])

L, C = map(int,input().split())
lst = sorted(list(input().split(" ")))
ja = ["a", "e", "i", "o", "u"]

bin = []
dfs(0, bin, "")

print('\n'.join(bin))
