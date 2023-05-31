from collections import defaultdict
from itertools import combinations

def subsequence_sum_2():
    N, S = map(int, input().split())
    nums = list(map(int, input().split()))
    
    if N == 1: 
        return 1 if nums[0] == S else 0
    
    mid = N // 2
    nums1, nums2 = nums[:mid], nums[mid:]
    
    sums1, sums2 = defaultdict(int), defaultdict(int)
    
    for i in range(1, mid + 1):
        for comb in combinations(nums1, i):
            sums1[sum(comb)] += 1
            
    for i in range(1, N - mid + 1):
        for comb in combinations(nums2, i):
            sums2[sum(comb)] += 1
            
    result = sums1[S] + sums2[S]
    for sum1 in sums1:
        if S - sum1 in sums2:
            result += sums1[sum1] * sums2[S - sum1]
            
    return result

print(subsequence_sum_2())
