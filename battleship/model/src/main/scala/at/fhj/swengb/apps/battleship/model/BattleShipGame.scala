package at.fhj.swengb.apps.battleship.model

import scala.util.Random

case class BattleShipGame(playerOne: String,
                          playerTwo: String,
                          playerOneField: BattleField,
                          playerTwoField: BattleField,
                          getCellWidth: Int => Double,
                          getCellHeight: Int => Double,
                          updateGameState: PlayerField => Unit,
                          log: String => Unit) {

  val gamePlayerA: PlayerField = fieldCreator3000(playerOne,playerTwoField,getCellWidth,getCellHeight,updateGameState,log)
  val gamePlayerB: PlayerField = fieldCreator3000(playerTwo,playerOneField,getCellWidth,getCellHeight,updateGameState,log)

  private def fieldCreator3000(commander: String,
                               battlefield: BattleField,
                               getCellWidth: Int => Double,
                               getCellHeight: Int => Double,
                               updateGameState: PlayerField => Unit,
                               log: String => Unit
                               ): PlayerField = {

    val battlefield: BattleField = BattleField(10, 10, Fleet(FleetConfig.Standard))

    PlayerField(BattleField.RandomPlacer3000(battlefield),
      log,
      updateGameState,
      getCellWidth,
      getCellHeight,
      commander)
  }


  private def battleNameCreator3000(): String = {

    val listOne: Seq[String] = Seq("Final", "Bloody", "Horrible", "Last")
    val listTwo: Seq[String] = Seq(" combat", " crusade", " bloodshed", " battle", " attack", " warfare")
    val listThree: Seq[String] = Seq(" in", " of", " for", " midst")
    val listFour: Seq[String] = Seq(" Lassnitzhoehe", " Gleisdorf", " Graz", " Urscha", " Puch", " Amstetten")

    val r: Random = new Random

    val a: Int = r.nextInt(3)
    val firstPart: String = listOne(a)

    val b: Int = r.nextInt(5)
    val firstMiddlePart: String = listTwo(b)

    val c: Int = r.nextInt(3)
    val lastMiddlePart: String = listThree(c)

    val d: Int = r.nextInt(3)
    val lastPart: String = listFour(d)

    val battleName: String = firstPart ++ firstMiddlePart ++ lastMiddlePart ++ lastPart
    battleName
  }

}
