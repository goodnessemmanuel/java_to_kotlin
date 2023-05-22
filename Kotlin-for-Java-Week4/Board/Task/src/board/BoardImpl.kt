package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

/**
 * Author - Oche Ejeh
 * Class implemented according
 * to the test cases
 */
class GameBoardImpl<T>(override val width: Int) : GameBoard<T>{
    private val mapGameBoardCells: MutableMap<Cell, T?> = mutableMapOf()
    private val boardType: SquareBoard = SquareBoardImpl(width)

    init {
       boardType.getAllCells()
           .forEach{ mapGameBoardCells[it] = null }
    }
    override fun get(cell: Cell): T? {
       return mapGameBoardCells[cell]
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        TODO("Not yet implemented")
    }

    override fun getCell(i: Int, j: Int): Cell {
        return boardType.getCell(i, j)
    }

    override fun getAllCells(): Collection<Cell> {
        TODO("Not yet implemented")
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("Not yet implemented")
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("Not yet implemented")
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return mapGameBoardCells.values.all(predicate)
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return mapGameBoardCells.values.any(predicate)
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
       return mapGameBoardCells
           .filter { (_, v) -> predicate(v) }
            .keys.firstOrNull()
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return mapGameBoardCells
            .filter { (_, v) -> predicate(v) }
              .map { it.key }
    }

    override fun set(cell: Cell, value: T?) {
        mapGameBoardCells[cell] = value
    }

}

class SquareBoardImpl (override val width: Int): SquareBoard {
    private var listOfCell: List<Cell> = listOf()
    init {
        listOfCell = (1..width)
            .flatMap { row -> (1..width)
                .map { col -> Cell(row, col) }
            }
    }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
      return  listOfCell.find { it.i == i && it.j == j }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return getCellOrNull(i, j)?: fail(i, j)
    }

    override fun getAllCells(): Collection<Cell> {
        return listOfCell
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
       return if (jRange.first < jRange.last) listOfCell
           .filter { it.i == i && it.j in jRange } else listOfCell
           .filter { it.i == i && it.j in jRange }.asReversed()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        return if (iRange.first < iRange.last) listOfCell
            .filter { it.i in iRange && it.j == j } else listOfCell
            .filter { it.i in iRange && it.j == j }.asReversed()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
      return  when (direction) {
            UP -> getCellOrNull(i - 1, j)
            DOWN -> getCellOrNull(i + 1, j)
            LEFT -> getCellOrNull(i, j - 1)
            RIGHT -> getCellOrNull(i, j + 1)
        }
    }

    private fun fail(i: Int, j: Int): Nothing = run {
           throw IllegalArgumentException("Cell with the coordinates $i, $j does not exist!")
    }
}


