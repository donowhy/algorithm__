cities = int(input())
distance = list(map(int,input().split()))
price = list(map(int,input().split()))

dist = []
ga = []
i = 0
hap = 0
while i < cities -1:
    if price[i] <= price[i+1]:
        dist.append(distance[i])
        ga.append(price[i])

    elif price[i] > price[i+1]:
        if dist and ga:
            dist.append(distance[i])
            hap += sum(dist) * ga[0]
            dist = []
            ga = []
        else:
            hap += distance[i] * price[i]

    i += 1

if dist and ga:
    hap += sum(dist) * ga[0]
print(hap)