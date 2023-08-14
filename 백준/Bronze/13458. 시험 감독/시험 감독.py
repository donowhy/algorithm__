# Input
n = int(input())  # Number of test sites
a = list(map(int, input().split()))  # List of examinees at each test site
b, c = map(int, input().split())  # Main and sub proctor's capacity

# Main Logic
result = n  # Since each test site needs at least one main proctor
for i in a:
    i -= b
    if i > 0:
        result += (i // c)
        if i % c:
            result += 1

# Print the result
print(result)
