import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private var n: Int = 0
private var m: Int = 0
private var l: Int = 0
private lateinit var map: Array<IntArray>
private var dx: IntArray = intArrayOf(1, 0, -1, 0)
private var dy: IntArray = intArrayOf(0, 1, 0, -1)

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (mVal, nVal, lVal) = br.readLine().split(" ").map { it.toInt() }
    m = mVal
    n = nVal
    l = lVal

    map = Array(m) { IntArray(n) }

    for (i in 0 until l) {
        val (x1, y1, x2, y2) = br.readLine().split(" ").map { it.toInt() }
        for (x in y1 until y2) {
            for (y in x1 until x2) {
                map[x][y] = -1
            }
        }
    }

    val areas = mutableListOf<Int>()
    for (i in 0 until m) {
        for (j in 0 until n) {
            if (map[i][j] == 0) {
                val area = bfs(i, j)
                areas.add(area)
            }
        }
    }

    areas.sort()
    println(areas.size)
    println(areas.joinToString(" "))
}

fun bfs(x: Int, y: Int): Int {
    val q: Queue<Node> = LinkedList()
    q.offer(Node(x, y))
    map[x][y] = 1
    var areaSize = 1

    while (q.isNotEmpty()) {
        val p = q.poll()
        for (i in 0 until 4) {
            val nx = dx[i] + p.x
            val ny = dy[i] + p.y

            if (nx < 0 || nx >= m || ny < 0 || ny >= n || map[nx][ny] != 0) continue

            map[nx][ny] = 1
            q.offer(Node(nx, ny))
            areaSize++
        }
    }

    return areaSize
}

data class Node(val x: Int, val y: Int)
