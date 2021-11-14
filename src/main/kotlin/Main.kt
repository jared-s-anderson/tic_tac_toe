import java.util.Scanner

private var board = Board(0)
private const val player_1 = "X"
private const val player_2 = "O"
private var currentTurn = ""
private var answer = ""
private var quit = ""

fun main()
{
    val scanner = Scanner(System.`in`)

    board = Board(3)
    board.displayBoard()

    while (!board.gameOver)
    {
        turns()
        println("$currentTurn's turn")
        println("Enter a row number:")
        val row = Integer.parseInt(scanner.nextLine())
        println("Enter a column number:")
        val column = Integer.parseInt(scanner.nextLine())

        /* 1 is subtracted so that users are not confused with
        the columns and rows going from 0 to 2. This will make
        it appear as if the rows and column go from 1 to 3.
        */
        board.placeLetter(row - 1, column - 1, currentTurn)
        if (board.gameOver)
        {
            println("Type the letter y and press enter to play again.")
            println("If you would like to quit the game, type the letter q and press enter.")
            val answer = scanner.nextLine()
            if (playAgain(answer)) {
                board.resetBoard()
            }
            if (quitGame(quit))
            {
                break
            }
        }
    }
}

fun turns()
{
    currentTurn = if (player_1 == currentTurn)
    {
        player_2
    }
    else
    {
        player_1
    }
}

fun playAgain(answer : String) : Boolean
{
    return (answer.equals("y", ignoreCase = true))
}

fun quitGame(quit : String) : Boolean
{
    return (quit.equals("q", ignoreCase = true))
}
