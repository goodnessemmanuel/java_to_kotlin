package games.gameOfFifteen

import board.Cell
import board.Direction
import board.GameBoard
import board.createGameBoard
import games.game.Game
import board.Direction.*

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen.create(initializer)

class GameOfFifteen
private constructor(private val initialState: GameOfFifteenInitializer) : Game {
    companion object {
        fun create (initialState: GameOfFifteenInitializer) : Game = GameOfFifteen(initialState)
    }
    private val gameBoard: GameBoard<Int?> = createGameBoard(4)

    override fun initialize() {
        gameBoard.populateGameFields(initialState)
    }

    override fun canMove(): Boolean {
        return true
    }

    override fun hasWon(): Boolean {
        var w = 1
        var isWinner = true
        for (i in 1..gameBoard.width) {
            for (j in 1..gameBoard.width) {
                if (w <= 15) {
                    if (w != gameBoard[gameBoard.getCell(i, j)]) {
                        isWinner = false
                        break
                    }
                    w++
                }
            }
        }
        return isWinner && get(gameBoard.width, gameBoard.width) == null
    }

    override fun processMove(direction: Direction) {
        gameBoard.startMove(direction)
    }

    override fun get(i: Int, j: Int): Int? = gameBoard.run { get(getCell(i, j)) }

}

private fun GameBoard<Int?>.switchRowValues(targetRowToUpdate: Cell, freeCells: List<Cell>) {
    val holder = this[targetRowToUpdate]
    this[targetRowToUpdate] = this[freeCells[0]]
    this[freeCells[0]] = holder
}

private fun GameBoard<Int?>.populateGameFields(initializer: GameOfFifteenInitializer) {
    val bucket = initializer.initialPermutation
    var p = 0
    for (i in 1..width) {
        for (j in 1..width) {
            var value: Int?
            if (p < 15) {
                value = bucket[p]
                p++
            } else {
                value = null
            }

            this[getCell(i, j)] = value

        }
    }

}

/**
 * compute direction based on game of 15 rules
 */
fun GameBoard<Int?>.startMove(direction: Direction) {

    val freeCells = getAllCells().filter {  this[it] == null }

    when (direction) {
        UP -> {
            val rowToBeUpdated = getCell(freeCells[0].i + 1, freeCells[0].j)
            switchRowValues(rowToBeUpdated, freeCells)
        }
        RIGHT -> {
            val targetRowToUpdate = getCell(freeCells[0].i, freeCells[0].j - 1)
            switchRowValues(targetRowToUpdate, freeCells)
        }
        LEFT -> {

            val targetRowToUpdate = getCell(freeCells[0].i, freeCells[0].j + 1)
            switchRowValues(targetRowToUpdate, freeCells)
        }
        DOWN -> {
            val rowToBeUpdated = getCell(freeCells[0].i - 1, freeCells[0].j)
            switchRowValues(rowToBeUpdated, freeCells)
        }
    }
}
