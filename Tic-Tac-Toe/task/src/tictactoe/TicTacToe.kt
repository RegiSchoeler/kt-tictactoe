package tictactoe

class TicTacToe() {
    private val gameBoard = GameBoard()
    private var gameLoopComplete = false
    var currentPlayerX = true


    /**
     * Display the state of the game and ask the user for input.
     */

    fun play() {
        print(gameState())
        do {
            getPlayerChoice()
            print(gameState())
        } while (!gameLoopComplete)
    }

    /**
     * Get the game board and check that the game is not complete, return it.
     */

    private fun gameState(): String {
        val message = gameBoard.formattedBoard()

        gameLoopComplete = when {
            gameBoard.isImpossible() -> true
            gameBoard.hasWinner() -> true
            gameBoard.isDraw() -> true
            else -> gameLoopComplete
        }

        return when {
            gameBoard.isImpossible() -> message + "Impossible\n"
            gameBoard.hasWinner() -> message + "${gameBoard.winningPlayer} wins\n"
            gameBoard.isDraw() -> message + "Draw\n"
            else -> message
        }
    }

    /**
     * Handle the input, provide user feedback if invalid.
     * 'q' or 'Q' to exit
     */

    private fun getPlayerChoice(): Boolean {
        if (currentPlayerX) print("Player X ") else print("Player O ")
        print("Enter the coordinates: ")
        val input = readLine()!!.split(' ').toList()

        when {
            input[0].isEmpty() -> print("You should enter coordinates!\n")
            input[0].matches(Regex("[qQ]")) -> gameLoopComplete = true
            input[0].matches(Regex("[a-zA-Z]+")) -> print("You should enter numbers!\n")
            input[0].matches(Regex("[^123]")) -> print("Coordinates should be from 1 to 3!\n")
            input[1].matches(Regex("[^123]")) -> print("Coordinates should be from 1 to 3!\n")
            input[0].toInt() in 1..3 && input[1].toInt() in 1..3 -> return when {
                gameBoard.moveUserNumbers(currentPlayerX, input[0].toInt(), input[1].toInt()) -> {
                    currentPlayerX = !currentPlayerX
                    true
                }
                else -> {
                    print("This cell is occupied! Choose another one!\n")
                    false
                }
            }
            else -> return false
        }
        return false
    }
}
