def dfs(hap, idx):
    global mx, mn

    if idx == n:
        mx = max(mx, hap)
        mn = min(mn, hap)
        return

    for i in range(4):
        if lst2[i] > 0:
            lst2[i] -= 1
            if i == 0:  # addition
                dfs(hap + lst1[idx], idx + 1)
            elif i == 1:  # subtraction
                dfs(hap - lst1[idx], idx + 1)
            elif i == 2:  # multiplication
                dfs(hap * lst1[idx], idx + 1)
            else:  # division
                if hap < 0:
                    dfs(-(-hap // lst1[idx]), idx + 1)  # handling negative numbers
                else:
                    dfs(hap // lst1[idx], idx + 1)
            lst2[i] += 1


n = int(input())
lst1 = list(map(int,input().split()))
lst2 = list(map(int,input().split()))
mx = -10 ** 9
mn = 10 ** 9
dfs(lst1[0], 1)
print(mx)
print(mn)
