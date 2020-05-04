object BoardDisplay {
  fun display(board: Array<Array<Boolean>>, trueValue: Char = '▉', falseValue: Char = ' ') {
    board.forEach { row ->
      row.forEach { column ->
        print(if(column) trueValue else falseValue)
      }
      println()
    }
  }
}