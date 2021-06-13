package tictactoe

class GameBoard() {
    private var currentBoard = "         "
    private var boardWithoutEnds = { boardRow(0) + boardRow(1) + boardRow(2) }
    private var countO = currentBoard.filter { it == 'O' }.count()
    private var countX = currentBoard.filter { it == 'X' }.count()

    var winningPlayer = ""
    var formattedBoard = { "---------\n${boardWithoutEnds()}---------\n" }

    /**
     * Public status functions.
     */

    fun isImpossible(): Boolean = !(!multipleWinners() && countO - countX in -1..1)
    fun hasWinner() = (hasWinnerO() || hasWinnerX())
    fun isDraw() = (countO - countX in -1..1) && !currentBoard.contains(" ")

    /**
     * The only way to modify the game board.
     */

    fun moveUserNumbers(isPlayerX: Boolean, row: Int, col: Int): Boolean {
        val positionNum = ((row - 1) * 3 + col - 1)
        return when (currentBoard[positionNum]) {
            'O' -> false
            'X' -> false
            ' ' -> {
                val tempBoard = currentBoard.slice(0 until positionNum) + when {
                    isPlayerX -> "X"
                    else -> "O"
                } + currentBoard.slice(positionNum + 1..8)
                currentBoard = tempBoard
                true
            }
            else -> false
        }
    }

    /**
     * Internal Functions to sort the board into line chunks.
     */

    private fun row(rowIndex: Int): String = currentBoard.chunked(3)[rowIndex]
    private fun col(colIndex: Int): String = currentBoard.slice(colIndex..8 step 3)
    private fun leftDiagonal(): String = currentBoard.slice(0..8 step 4)
    private fun rightDiagonal(): String = currentBoard.slice(2..6 step 2)
    private fun boardRow(rowIndex: Int): String = row(rowIndex).toCharArray()
            .joinToString(" ", "| ", " |\n")

    /**
     * Line chunk checks
     */
    private fun checkLine(sortedLine: String): Boolean = when {
        isXWin(sortedLine) -> true
        isOWin(sortedLine) -> true
        else -> false
    }

    private fun isXWin(sortedLine: String) = when (sortedLine) {
        "XXX" -> {
            winningPlayer = "X"
            true
        }
        else -> false
    }

    private fun isOWin(sortedLine: String) = when (sortedLine) {
        "OOO" -> {
            winningPlayer = "O"
            true
        }
        else -> false
    }

    /**
     * Internal game state checks - lambda functions
     */

    private var multipleWinners = {
        var caseTest = false
        caseTest = when (2) {
            countRowWins() -> true
            countColumnWins() -> true
            countCrossWins -> true
            else -> false
        }
        caseTest
    }

    private var countCrossWins = if (checkLine(leftDiagonal()) && checkLine(rightDiagonal())) 2 else
        if (checkLine(leftDiagonal()) || checkLine(rightDiagonal())) 1 else 0

    private var countRowWins = {
        var rowCount = 0
        for (rows in 0..2) when {
            checkLine(row(rows)) -> rowCount++
        }
        rowCount
    }

    private var countColumnWins = {
        var colCount = 0
        for (cols in 0..2) when {
            checkLine(col(cols)) -> colCount++
        }
        colCount
    }

    private val hasWinnerX = {
        var winner = false
        if (isXWin(rightDiagonal()) || isXWin(leftDiagonal())) winner = true
        for (rowAndCols in 0..2) {
            if (isXWin(row(rowAndCols))) winner = true
            if (isXWin(col(rowAndCols))) winner = true
        }
        winner
    }

    private val hasWinnerO = {
        var winner = false
        if (isOWin(rightDiagonal()) || isOWin(leftDiagonal())) winner = true
        for (rowAndCols in 0..2) {
            if (isOWin(row(rowAndCols))) winner = true
            if (isOWin(col(rowAndCols))) winner = true
        }
        winner
    }
}
