import heapq
import sys
input = sys.stdin.readline

# 입력
V, E = map(int, input().split())
K = int(input())  # 시작점

graph = [[] for _ in range(V+1)]
for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w)) # 도착지, 가중치


# 다익스트라 최적경로 탐색
def dijkstra(graph, start):
    distances = [int(1e9)] * (V+1)  # 처음 초기값은 무한대
    distances[start] = 0  # 시작 노드까지의 거리는 0
    queue = []
    heapq.heappush(queue, [distances[start], start])  # 시작 노드부터 탐색 시작

    while queue:  # queue에 남아있는 노드가 없을 때까지 탐색

        dist, node = heapq.heappop(queue)  # 탐색할 거리, 노드
        # 기존 최단거리보다 멀다면 무시
        if distances[node] < dist:
            continue

        # 노드와 연결된 인접노드 탐색
        for next_node, next_dist in graph[node]:
            distance = dist + next_dist  # 인접노드까지의 거리
            if distance < distances[next_node]:  # 기존 거리 보다 짧으면 갱신
                distances[next_node] = distance
                heapq.heappush(queue, [distance, next_node])  # 다음 인접 거리를 계산 하기 위해 큐에 삽입
    return distances

distances = dijkstra(graph, K)
for i in range(1, V+1):
    print("INF" if distances[i] == int(1e9) else distances[i])