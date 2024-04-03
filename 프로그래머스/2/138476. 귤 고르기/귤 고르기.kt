class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        
        val count = HashMap<Int, Int>()
        for (i in tangerine) {
            count[i] = count.getOrDefault(i, 0) + 1
        }
        
        var totalCount = 0
        var resultCount = 0
        for ((_, value) in count.entries.sortedByDescending { it.value }) {
            totalCount += value
            resultCount++
            if (totalCount >= k) break
        }
        
        return resultCount
    }
}
