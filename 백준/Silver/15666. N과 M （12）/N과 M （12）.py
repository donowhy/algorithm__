def bt(seq, cnt):
    cnt -= 1
    if cnt == -1:
        print(*seq)
        return
    
    for i in arr:
        if not seq or i >= seq[-1]:
            bt(seq+[i],cnt)

n,m = map(int,input().split())
arr = sorted(list(set(map(int,input().split()))))
bt([],m)