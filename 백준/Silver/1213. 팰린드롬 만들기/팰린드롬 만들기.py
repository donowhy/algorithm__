a = dict()
b = input()

for ch in b:
    if ch in a:
        a[ch] += 1
    else:
        a[ch] = 1

if sum(i % 2 for i in a.values()) > 1:
    print("I'm Sorry Hansoo")
else:
    half = ''
    for k, v in sorted(a.items()): 
        half += k * (v//2)

    ans = half
    for k, v in a.items():
        if v%2:
            ans += k
            break
    ans += ''.join(reversed(half))
    print(ans)