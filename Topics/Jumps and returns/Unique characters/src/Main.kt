fun main() {
    val input = readLine()!!.split(" ")
    val word = input[0]
    var countOfSoloUniques = 0

    NextChar@for ((parentIndex, char) in word.toCharArray().withIndex()) {
//      if (!char.isLetter()) continue@NextChar
        for (index in word.indices) {
            when {
                index == word.lastIndex && parentIndex == index -> countOfSoloUniques++
                char == word[index] && index != parentIndex -> continue@NextChar
                index == word.lastIndex && char != word.last() -> countOfSoloUniques++
                else -> print("")
            }
        }
    }

    print(countOfSoloUniques)
}