fun main() {
    var sum = 0
    var notZero = true
    while (notZero) {
        val add = readLine()!!.toInt()
        sum += add
        if (add == 0) {
            print(sum)
            notZero = false
        }
    }
}