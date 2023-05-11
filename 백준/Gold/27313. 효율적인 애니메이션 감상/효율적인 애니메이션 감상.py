def can_watch(num):
    groups = []
    for srt_i in range(num, N, K):
        groups.append(L[srt_i])

    total = 0
    for i in range(len(groups) - 1, -1, -1):
        total += groups[i]

    return total <= M


def find_cnt():
    start, end = 0, len(L) - 1

    while start < end:
        mid = (start + end) // 2

        if can_watch(mid):
            end = mid
        else:
            start = mid + 1
    if can_watch(end):
        return len(L) - end
    else:
        return 0


N, M, K = map(int,input().split())
L = list(map(int,input().split()))
L.sort(reverse=True)
print(find_cnt())