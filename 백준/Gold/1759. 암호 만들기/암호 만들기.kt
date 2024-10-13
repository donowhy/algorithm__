import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var arr: Array<String>
private const val NEEDS = 2
private val result = mutableSetOf<String>()
private const val AEIOU = "aeiou"

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (l, c) = br.readLine().split(" ").map { it.toInt() }

    arr = br.readLine().split(" ").toTypedArray()
    arr.sort()

    dfs("", 0, 0, 0, l)

    result.forEach { println(it) }
}

private fun dfs(lng: String, start: Int, vowels: Int, consonants: Int, l: Int) {
    if (lng.length == l) {
        if (vowels >= 1 && consonants >= NEEDS) {
            result.add(lng)
        }
        return
    }

    for (i in start until arr.size) {
        val next = arr[i]
        if (AEIOU.contains(next)) {
            dfs(lng + next, i + 1, vowels + 1, consonants, l)
        } else {
            dfs(lng + next, i + 1, vowels, consonants + 1, l)
        }
    }
}
