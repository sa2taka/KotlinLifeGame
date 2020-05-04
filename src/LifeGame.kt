class LifeGame(private val width: Int, private val height: Int) {
  var board: Array<Array<Boolean>> = arrayOf()
    private set
  var turn: Int = 0
    private set

  init {
    initializeBoard()
  }

  fun initializeBoard(threshold: Double = 0.1) {
    board = Array(height) { _ -> Array(width) { _ -> Math.random() < threshold } }
  }

  fun initializeBoard(boardStr: String, trueChar: Char = '*') {
    val orgBoard = formatBoardFrom(boardStr, trueChar)
    val orgBoardHeight = orgBoard.size
    val orgBoardWidth = orgBoard[0].size
    board = Array(height) { y -> Array(width) { x -> orgBoard[y % orgBoardHeight][x % orgBoardWidth] } }
  }

  fun next(): Array<Array<Boolean>> {
    var newBoard = Array(height) { _ -> Array(width) { false } }
    board.forEachIndexed { y, row ->
      row.forEachIndexed { x, column ->
        newBoard[y][x] = when(countAroundAliveCell(x, y)) {
          2 -> column
          3 -> true
          else -> false
        }
      }
    }
    turn += 1
    board = newBoard
    return board
  }

  private fun formatBoardFrom(boardStr: String, trueChar: Char = '*'): List<List<Boolean>> {
    return boardStr.split("\n").map { line ->
      line.toCharArray().map { char ->
        char == trueChar
      }
    }
  }

  private fun countAroundAliveCell(x: Int, y: Int): Int {
    val sides: List<Pair<Int, Int>> = listOf(-1, 0, 1).flatMap<Int,Pair<Int, Int>> { y ->
      listOf(-1, 0, 1).map<Int, Pair<Int, Int>> { x ->
        x to y
      }
    }

    var count = 0

    sides.forEach { (sx, sy) ->
      if(sx == 0 && sy == 0) {
        return@forEach
      }

      count += if(board[(height + y + sy) % height][(width + x + sx) % width]) 1 else 0
    }

    return count
  }
}