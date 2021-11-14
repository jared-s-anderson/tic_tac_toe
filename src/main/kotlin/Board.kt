import kotlin.math.pow

class Board(private val num : Int) {
    private var moves = 0
    var gameOver = false
    private val vacant = "___"
    private var board = Array(num) { Array(num) { vacant } }

    private fun reset() {
        board = Array(num) { Array(num) { vacant } }
    }

    // This loops through the array
    fun displayBoard()
    {
        board.forEach { row ->
            row.forEach { space ->
                if (space == vacant) {
                    print("|$space|")
                } else {
                    print("| $space |")
                }
            }
            println() }
        println()
    }

    fun placeLetter(x: Int, y: Int, move: String)
    {
        /* This checks to see if the game has ended, and it checks if
        a spot has been filled so that a player is not able to place
        a letter on a spot that is taken
        */
        if (!gameOver && validPosition(x, y) && board[x][y] == vacant)
        {
            moves++
            board[x][y] = move
            displayBoard()


            // If a player wins or there is a draw, the game ends.
            gameOver = isWinner(x, y, move) || gameDraw()
            if (gameOver && !gameDraw())
            {
                println("A winner has been determined!")
            }
            else if (gameDraw())
            {
                println("There is no winner. This game has ended in a draw.")
            }
        }
    }

    private fun validPosition(x: Int, y: Int) : Boolean
    {
        // Checks to see if x and y are between 0 and num - 1
        return ((x in 0 until num) && (y in 0 until num))
    }

    private fun isWinner(x: Int, y: Int, move: String) : Boolean
    {
        // This checks to see if the same character is present in all 3 of the rows
        for (row in 0 until num)
        {
            // row is used to represent the changing row.
            if (board[x][row] != move)
            {
                break
            }

            if (row == num - 1)
            {
                return true
            }
        }

        // This checks to see if the same character is present in all 3 of the columns.
        for (col in 0 until num)
        {
            if (board[col][y] != move)
            {
                break
            }

            if (col == num - 1)
            {
                return true
            }
        }

        // This checks to see if the same character letter is diagonal across the board.
        if (x == y)
        {
            for (d in 0 until num)
            {
                if (board[d][d] != move)
                {
                    break
                }

                if (d == num - 1)
                {
                    return true
                }
            }
        }

        // This checks for anti-diagonal
        if (x + y == num - 1)
        {
            for (d in 0 until num)
            {
                if (board[d][(num - 1) - d] != move)
                {
                    break
                }

                if (d == num - 1)
                {
                    return true
                }
            }
        }

        // if none of the options above are demonstrated, false is returned.
        return false
    }

    fun resetBoard()
    {
        reset()
        gameOver = false
        moves = 0
    }

    /* This checks to see how many moves are made, and if 8 or more moves
    have been made, there is a draw.*/
    private fun gameDraw() : Boolean
    {
        return (moves == (num.toDouble().pow(2) - 1).toInt())
    }
}