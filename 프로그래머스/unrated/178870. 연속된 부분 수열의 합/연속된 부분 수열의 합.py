def solution(sequence, target):
    start = 0
    end = 0
    hap = 0
    min_length = float('inf')
    res = []

    while end < len(sequence):
        while hap < target and end < len(sequence):
            hap += sequence[end]
            end += 1

        while hap >= target and start < end:
            if hap == target:
                if end - start < min_length:
                    min_length = end - start
                    res = [start, end - 1]
            hap -= sequence[start]
            start += 1

    if not res:
        return []  # No subsequence found

    return res
