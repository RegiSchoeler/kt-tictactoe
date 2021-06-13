// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
    val beyondTheWall = readLine()!!.split(',').map { it }.toTypedArray()
    val backFromTheWall = readLine()!!.split(',').map { it }.toTypedArray()
    // do not touch the lines above
    // write your code here

    if (backFromTheWall.contentEquals(beyondTheWall)) print(true) else print(false)
}
