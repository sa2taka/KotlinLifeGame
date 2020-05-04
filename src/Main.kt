fun main() {
  val lifeGame = LifeGame(20, 20)

  val boardStr = """
    -----------
    -----------
    --*--------
    ---*-------
    -***-------
    -----------
    -----------
    -----------
  """.trimIndent()
  lifeGame.initializeBoard(boardStr)

  BoardDisplay.display(lifeGame.board)

  while(true) {
    Thread.sleep(500)
    println(lifeGame.turn)
    BoardDisplay.display(lifeGame.next())
  }
}
