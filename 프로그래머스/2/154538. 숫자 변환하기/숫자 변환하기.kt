import java.util.*

class Solution {
    
    lateinit var ans: ArrayList<Int>
    lateinit var q : Queue<Pair<Int, Int>>
    lateinit var visited: HashMap<Int, Boolean>
    
    fun solution(x: Int, y: Int, n: Int): Int {
        ans = arrayListOf()
        q = LinkedList()
        visited = hashMapOf()
        
        bfs(x, y, n, 0)
        
        println(ans.toString())
        
        if (ans.isEmpty()) {
            return -1
        } else {        
            return ans.first()
        }
    }
    
    fun bfs(x: Int, y: Int, n: Int, depth: Int) {
        q.add(Pair(x, depth))
        
        while (q.isNotEmpty()) {
            val node = q.poll()
            
            if (node.first > y) continue
            if (node.first == y) {
                ans.add(node.second)
                break
            }
            
            if (!(visited[node.first + n] ?: false)) {
                q.add(Pair(node.first + n, node.second + 1))
                visited[node.first + n] = true
            }
            if (!(visited[node.first * 2] ?: false)) {
                q.add(Pair(node.first * 2, node.second + 1))
                visited[node.first * 2] = true
            }
            if (!(visited[node.first * 3] ?: false)) {
                q.add(Pair(node.first * 3, node.second + 1))
                visited[node.first * 3] = true
            }
        }
    }
}