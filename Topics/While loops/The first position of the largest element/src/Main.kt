fun main() {
    val input = generateSequence(::readLine)
    val lines = input.toList().map { it.toInt() }

    print(findMax(lines).toString() + " " + (lines.indexOf(findMax(lines)) + 1))
}

fun findMax(list: List<Int>): Int {
    var max = Int.MIN_VALUE
    for (i in list.plus(list[0])) {
        if (max < i)
            max = i
    }
    return max
}